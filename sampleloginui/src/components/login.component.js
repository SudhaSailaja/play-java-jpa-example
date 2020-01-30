import React, { Component } from "react";
//import SignUp from "../components/signup.component";

export default class Login extends Component {

    constructor(props) {
        super(props)
        this.state = {
            nameuser : '',
        }

        this.handleChange = this.handleChange.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    handleChange(event) {
        const { name, value } = event.target

        this.setState({
            [name] : value
        })
    }

    handleSubmit(event) {
        alert(this.state.nameuser + ' registered successfully!!!')
        event.preventDefault()
    }

    render() {
        return (
            <form onSubmit = { this.handleSubmit }>

                <center><h2>KNOW YOUR LEADER</h2></center>
                <br/>
                <hr/>
                <br/>
                <h3>Login</h3>

                <div className="form-group">
                    <label>User name</label>
                    <input name = 'nameuser' type="name" className="form-control" value = { this.state.nameuser } onChange = { this.handleChange }/>
                </div>

                <div className="form-group">
                    <label>Password</label>
                    <input name = 'pwd' type="password" className="form-control" />
                </div>

                <div className="form-group">
                    <div className="custom-control custom-checkbox">
                        <input type="checkbox" name = 'check' className="custom-control-input" id="customCheck1" />
                        <label className="custom-control-label" htmlFor="customCheck1">Remember me</label>
                    </div>
                </div>

                <button type="submit" className="btn btn-danger btn-block" >Submit</button>
                <p className="forgot-password text-right">
                    Forgot <a href="#">password?</a>
                </p>



                <p className="forgot-password text-right">
                    If not registered <a href="/sign-up">Signup?</a>
                </p>

            </form>

        );
    }
}