package controllers;

import models.Person;
import models.PersonRepository;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import play.libs.Json;
import static play.libs.Json.toJson;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * The controller keeps all database operations behind the repository, and uses
 * {@link play.libs.concurrent.HttpExecutionContext} to provide access to the
 * {@link play.mvc.Http.Context} methods like {@code request()} and {@code flash()}.
 */
public class PersonController extends Controller {

    private final FormFactory formFactory;
    private final PersonRepository personRepository;
    private final HttpExecutionContext ec;


    @Inject
    public PersonController(FormFactory formFactory, PersonRepository personRepository, HttpExecutionContext ec) {
        this.formFactory = formFactory;
        this.personRepository = personRepository;
        this.ec = ec;
    }

    public Result index() {
        return ok(views.html.index.render());
    }

    public CompletionStage<Result> addPerson() {
        Person person1 = formFactory.form(Person.class).bindFromRequest().get();
        return personRepository.add(person1).thenApplyAsync(p -> {
            return redirect(routes.PersonController.index());
        }, ec.current());
    }

    public CompletionStage<Result> getPersons() {
        return personRepository.list().thenApplyAsync(personStream -> {
            return ok(toJson(personStream.collect(Collectors.toList())));
        }, ec.current());
    }
    public CompletionStage<Result> addPersonJson() {
        JsonNode requestJson = request().body().asJson();
        Person person = Json.fromJson(requestJson,Person.class);
        String firstName = null;
        //if (requestJson.has("name")) {
            //firstName = requestJson.get("name").asText();
            firstName = Json.toJson(person.name).asText();
            person.setName(firstName);
            String message = "Added successfully person " + firstName;
            return personRepository.add(person).thenApplyAsync(p -> {
                return ok("Added successfully "+Json.toJson(person.name));
            }, ec.current());
    }
    public CompletionStage<Result> delete(String uname){
        String message = "Deleted successfully "+uname;
        return personRepository.del(uname).thenApplyAsync(p -> {
            return ok(message);
        },ec.current());
    }

}
