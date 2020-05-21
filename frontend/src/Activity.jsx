import React from "react";
import {Button} from "@material-ui/core";
import TextField from "@material-ui/core/TextField";
import {MuiPickersUtilsProvider, KeyboardDatePicker} from '@material-ui/pickers';
import Grid from "@material-ui/core/Grid";
import DateFnsUtils from '@date-io/date-fns';
import {api_url} from "./helpers";

const initialState = {
    userId: 1,
    weight: 0,
    step: 0,
    water: 0,
    date: null
};

const initialErrors = {
    weight: 1,
    step: 1,
    water: 1,
    date: 1,
};

export class Activity extends React.Component{

    errors = {...initialErrors};

    constructor(props){
        super(props);
        this.state = initialState;
        this.handleChange = this.handleChange.bind(this);
        this.handleDateChange = this.handleDateChange.bind(this);
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

    handleDateChange(date) {
        this.setState({ date: date });
    }

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
        data.date = data.date.getTime();
        console.log(JSON.stringify(data));
        fetch(api_url + '/saveActivity/', {
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
        return (
            <div className="content container">
                <div className={"title d-flex flex-column col-10 m-auto pt-4"}>
                    <h3>Activity</h3>
                </div>
                <form className="d-flex flex-column col-10 m-auto pt-2">
                    <TextField onChange={this.handleChange} value={this.state.weight}
                               error={this.state.weight || this.errors.weight ? false:true}
                               className="pb-2" required name="weight" id="weight" label="Weight"/>
                    <TextField onChange={this.handleChange} value={this.state.step}
                               error={this.state.step || this.errors.step ? false:true}
                               className="pb-2" required name="step" id="step" label="Step"/>
                    <TextField onChange={this.handleChange} value={this.state.water}
                               error={this.state.water || this.errors.water ? false:true}
                               className="pb-2" required name="water" id="water" label="Water Intake"/>
                    <MuiPickersUtilsProvider utils={DateFnsUtils}>
                        <Grid container justify="space-around">
                            <KeyboardDatePicker
                                disableToolbar
                                variant="inline"
                                format="MM/dd/yyyy"
                                margin="normal"
                                id="date"
                                label="Date"
                                value={this.state.date}
                                onChange={this.handleDateChange}
                                KeyboardButtonProps={{
                                    'aria-label': 'change date',
                                }}
                            />
                        </Grid>
                    </MuiPickersUtilsProvider>

                    <Button variant="outlined" color="primary" onClick={this.onSubmit}>Submit</Button>
                </form>
            </div>
        )
    }
}
