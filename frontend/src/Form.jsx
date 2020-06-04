import React from "react";
import TextField from "@material-ui/core/TextField";
import MenuItem from "@material-ui/core/MenuItem";
import Select from "@material-ui/core/Select";
import InputLabel from "@material-ui/core/InputLabel";
import FormControl from "@material-ui/core/FormControl";
import {Button} from "@material-ui/core";
import {api_url} from "./helpers";
import axios from 'axios';

const initialState = {
    userId: 1,
    firstName: '',
    lastName: '',
    email: '',
    age: 0,
    gender: '',
    height: 0,
    lifestyle: '',
};

const initialErrors = {
    firstName: 1,
    lastName: 1,
    email: 1,
    age: 1,
    gender: 1,
    height: 1,
    lifestyle: 1,
};

export class Form extends React.Component{

    errors = {...initialErrors};

    constructor(props){
        super(props);
        this.state = initialState;
        this.handleChange = this.handleChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);

    }

    handleChange(evt) {
        const value = evt.target.value;
        this.setState({
            ...this.state,
            [evt.target.name]: value
        });
        this.errors[evt.target.name] = 0;
    };

    onSubmit = (e) => {
        e.preventDefault();
        for(const v in this.state)
        {
            if(!this.state[v])
                return;
        }
        this.setState(initialState);
        this.errors = {...initialErrors};
        let data = this.state;

        fetch( api_url +'/submitForm/', {
            method: 'POST',
            headers: {
                'Accept': '*/*',
                'Content-Type': 'application/json',
                'Connection': 'keep-alive'
            },
            body: JSON.stringify(data)
        }).then(res => {
                console.log(res);
                console.log(res.data);
            })
    };

    render() {
        return <div>
            <form className="d-flex flex-column col-10 m-auto pt-4">
                <TextField onChange={this.handleChange} value={this.state.firstName}
                           error={this.state.firstName || this.errors.firstName ? false:true}
                           className="pb-2" required name="firstName" id="firstName" label="First name"/>
                <TextField value={this.state.lastName} onChange={this.handleChange}
                           error={this.state.lastName || this.errors.lastName ? false:true}
                           className="pb-2" required name="lastName" id="lastName" label="Last name"/>
                <TextField value={this.state.email} onChange={this.handleChange}
                           error={this.state.email || this.errors.email ? false:true}
                           className="pb-2" required name="email" id="email" label="Email" type="email"/>
                <TextField value={this.state.age? this.state.age : ''} onChange={this.handleChange}
                           error={this.state.age || this.errors.age ? false:true}
                           className="pb-2" required name="age" id="age" label="Age" type="number"/>
                <FormControl className="pb-2">
                    <InputLabel id="gender-label">Gender</InputLabel>
                    <Select
                        MenuProps={{
                            getContentAnchorEl: null,
                            anchorOrigin: {
                                vertical: "bottom",
                                horizontal: "left",
                            }
                        }}
                        labelId="gender-label"
                        id="gender"
                        name="gender"
                        error={this.state.gender || this.errors.gender ? false:true}
                        value={this.state.gender}
                        onChange={this.handleChange}>
                        <MenuItem value={"m"}>Male</MenuItem>
                        <MenuItem value={"f"}>Female</MenuItem>
                        <MenuItem value={"o"}>Other</MenuItem>
                    </Select>
                </FormControl>
                <TextField value={this.state.height? this.state.height : ''} onChange={this.handleChange}
                           error={this.state.height || this.errors.height ? false:true}
                           className="pb-4" required name="height" id="height" label="Height" type="number"/>
                <FormControl className="pb-2">
                    <InputLabel id="lifestyle-label">Lifestyle</InputLabel>
                    <Select
                        MenuProps={{
                            getContentAnchorEl: null,
                            anchorOrigin: {
                                vertical: "bottom",
                                horizontal: "left",
                            }
                        }}
                        labelId="lifestyle-label"
                        id="lifestyle"
                        name="lifestyle"
                        error={this.state.lifestyle || this.errors.lifestyle ? false:true}
                        value={this.state.lifestyle}
                        onChange={this.handleChange}>
                        <MenuItem value={"h"}>High activity</MenuItem>
                        <MenuItem value={"m"}>Medium activity</MenuItem>
                        <MenuItem value={"l"}>Low activity</MenuItem>
                    </Select>
                </FormControl>
                <Button variant="outlined" color="primary" onClick={this.onSubmit}>Submit</Button>
            </form>
        </div>
    }
}
