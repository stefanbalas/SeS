import React from "react";
import TextField from "@material-ui/core/TextField";
import MenuItem from "@material-ui/core/MenuItem";
import Select from "@material-ui/core/Select";
import InputLabel from "@material-ui/core/InputLabel";
import FormControl from "@material-ui/core/FormControl";
import {Button} from "@material-ui/core";

export class Form extends React.Component{

    constructor(props){
        super(props);
        this.state = {
            firstName: '',
            lastName: '',
            email: '',
            age: 0,
            gender: '',
            height: 0,
        };
        this.handleChange = this.handleChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);

    }

    handleChange(evt) {
        const value = evt.target.value;
        this.setState({
            ...this.state,
            [evt.target.name]: value
        });
    };

    onSubmit = (e) => {
        e.preventDefault();
        console.log(this.state)
    };

    render() {
        return <div>
            <form className="d-flex flex-column col-10 m-auto pt-4">
                <TextField onChange={this.handleChange} value={this.state.firstName}
                           className="pb-2" required name="firstName" id="firstName" label="First name"/>
                <TextField value={this.state.lastName} onChange={this.handleChange}
                           className="pb-2" required name="lastName" id="lastName" label="Last name"/>
                <TextField value={this.state.email} onChange={this.handleChange}
                           className="pb-2" required name="email" id="email" label="Email" type="email"/>
                <TextField value={this.state.age? this.state.age : ''} onChange={this.handleChange}
                           className="pb-2" required name="age" id="age" label="Age" type="number"/>
                <FormControl className="pb-2">
                    <InputLabel id="gender-label">Gender</InputLabel>
                    <Select
                        labelId="gender-label"
                        id="gender"
                        name="gender"
                        value={this.state.gender}
                        onChange={this.handleChange}>
                        <MenuItem value={"m"}>Male</MenuItem>
                        <MenuItem value={"f"}>Female</MenuItem>
                        <MenuItem value={"o"}>Other</MenuItem>
                    </Select>
                </FormControl>
                <TextField value={this.state.height? this.state.height : ''} onChange={this.handleChange}
                           className="pb-4" required name="height" id="height" label="Height" type="number"/>
                <Button variant="outlined" color="primary" onClick={this.onSubmit}>Submit</Button>
            </form>
        </div>
    }
}