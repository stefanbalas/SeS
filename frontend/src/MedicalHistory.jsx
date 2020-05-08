import React from "react";
import {Button} from "@material-ui/core";
import TextField from "@material-ui/core/TextField";
import DateFnsUtils from "@date-io/date-fns";
import Grid from "@material-ui/core/Grid";
import {KeyboardDatePicker, MuiPickersUtilsProvider} from "@material-ui/pickers";
import {api_url} from "./helpers";

const initialState = {
    userId: 1,
    name: '',
    lastVisit: null,
    recommendVisit: null
};

const initialErrors = {
    name: 1,

};

export class MedicalHistory extends React.Component {

    errors = {...initialErrors};

    constructor(props){
        super(props);
        this.state = initialState;
        this.handleChange = this.handleChange.bind(this);
        this.handleDateChange = this.handleDateChange.bind(this);
        this.handleEndDateChange = this.handleEndDateChange.bind(this);
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
        this.setState({ lastVisit: date });
    }

    handleEndDateChange(date){
        this.setState({ recommendVisit: date });
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
        data.lastVisit = data.lastVisit.getTime();
        data.recommendVisit = data.recommendVisit.getTime();
        console.log(JSON.stringify(data));
        fetch(api_url + '/saveHistory/', {
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
                    <h3>Medical History</h3>
                </div>
                <form className="d-flex flex-column col-10 m-auto pt-2">
                <TextField onChange={this.handleChange} value={this.state.name}
                           error={this.state.name || this.errors.name ? false:true}
                           className="pb-2" required name="name" id="name" label="Name"/>
                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                    <Grid container justify="space-around">
                        <KeyboardDatePicker
                            disableToolbar
                            variant="inline"
                            format="MM/dd/yyyy"
                            margin="normal"
                            id="lastVisit"
                            label="Last visit"
                            value={this.state.lastVisit}
                            onChange={this.handleDateChange}
                            KeyboardButtonProps={{
                                'aria-label': 'change date',
                            }}
                        />
                    </Grid>
                </MuiPickersUtilsProvider>
                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                    <Grid container justify="space-around">
                        <KeyboardDatePicker
                            disableToolbar
                            variant="inline"
                            format="MM/dd/yyyy"
                            margin="normal"
                            id="recommendVisit"
                            label="Recommended Date"
                            value={this.state.recommendVisit}
                            onChange={this.handleEndDateChange}
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
