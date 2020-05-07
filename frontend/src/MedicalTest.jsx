import React from "react";
import {Button} from "@material-ui/core";
import TextField from "@material-ui/core/TextField";
import Container from "@material-ui/core/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import {MuiPickersUtilsProvider, KeyboardDatePicker} from '@material-ui/pickers';
import Grid from "@material-ui/core/Grid";
import DateFnsUtils from '@date-io/date-fns';

const initialState = {
    userId: 1,
    name: '',
    value: 0,
    minValue: 0,
    maxValue: 0,
    date: null
};

const initialErrors = {
    name: 1,
    value: 1,
    minValue: 1,
    maxValue: 1,
    date: 1,

};

export class MedicalTest extends React.Component{

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
        data.value = parseFloat(data.value.replace(",", "."));
        data.minValue = parseFloat(data.minValue.replace(",", "."));
        data.maxValue = parseFloat(data.maxValue.replace(",", "."));
        data.date = data.date.getTime();
        console.log(JSON.stringify(data));
        fetch('http://localhost:8080/saveAnalize/', {
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
                <h1>Medical Tests</h1>
                <form className="d-flex flex-column col-10 m-auto pt-4">
                    <TextField onChange={this.handleChange} value={this.state.name}
                               error={this.state.name || this.errors.name ? false:true}
                               className="pb-2" required name="name" id="name" label="Name"/>
                    <TextField onChange={this.handleChange} value={this.state.value}
                               error={this.state.value || this.errors.value ? false:true}
                               className="pb-2" required name="value" id="value" label="Value"/>
                    <span className="reference-values">Reference Values:</span>
                    <Container>
                        <Row>
                            <Col>
                                <TextField onChange={this.handleChange} value={this.state.minValue}
                                           error={this.state.minValue || this.errors.minValue ? false:true}
                                           className="pb-2" required name="minValue" id="minValue" label="Min Value"/>
                            </Col>
                            <Col>
                                <TextField onChange={this.handleChange} value={this.state.maxValue}
                                           error={this.state.maxValue || this.errors.maxValue ? false:true}
                                           className="pb-2" required name="maxValue" id="maxValue" label="Max Value"/>
                            </Col>
                        </Row>
                    </Container>
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
