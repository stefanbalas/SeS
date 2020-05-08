import React from "react";
import { purple } from '@material-ui/core/colors';
import InputLabel from "@material-ui/core/InputLabel";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import FormControl from "@material-ui/core/FormControl";
import RadioGroup from '@material-ui/core/RadioGroup';
import FormLabel from "@material-ui/core/FormLabel";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Radio from "@material-ui/core/Radio";
import { withStyles } from '@material-ui/core/styles';
import TextField from "@material-ui/core/TextField";
import {Button} from "@material-ui/core";
import {api_url} from "./helpers";


const PurpleRadio = withStyles({
    root: {
        color: purple[400],
        '&$checked': {
            color: purple[600],
        },
    },
    checked: {},
})((props) => <Radio color="default" {...props} />);

const initialState = {
    userId: 1,
    jobActivity: '',
    freetimeActivity: '',
    practicesSport: '',
    timpMediuActivitate: 0,
    timpCalculator: 0
};

const initialErrors = {
    jobActivity: 1,
    freetimeActivity: 1,
    practicesSport: 1,
    timpMediuActivitate: 1,
    timpCalculator: 1
};

export class Lifestyle extends React.Component {

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
        data.practicesSport = parseInt(data.practicesSport);
        data.timpMediuActivitate = parseInt(data.timpMediuActivitate);
        data.timpCalculator = parseInt(data.timpCalculator);
        console.log(JSON.stringify(data));
        fetch(api_url + '/saveLifestyle/', {
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
                    <h3>Lifestyle</h3>
                </div>
                <form className="d-flex flex-column col-10 m-auto pt-2">
                    <FormControl className="pb-2">
                        <InputLabel id="job-label">Job Activity</InputLabel>
                        <Select
                            MenuProps={{
                                getContentAnchorEl: null,
                                anchorOrigin: {
                                    vertical: "bottom",
                                    horizontal: "left",
                                }
                            }}
                            labelId="job-label"
                            id="jobActivity"
                            name="jobActivity"
                            error={this.state.jobActivity || this.errors.jobActivity ? false:true}
                            value={this.state.jobActivity}
                            onChange={this.handleChange}>
                            <MenuItem value={1}>Sedentary</MenuItem>
                            <MenuItem value={2}>Semi-active</MenuItem>
                            <MenuItem value={3}>Active</MenuItem>
                        </Select>
                    </FormControl>
                    <FormControl className="pb-2">
                        <InputLabel id="freetimeActivity-label">Leisure Time Activity</InputLabel>
                        <Select
                            MenuProps={{
                                getContentAnchorEl: null,
                                anchorOrigin: {
                                    vertical: "bottom",
                                    horizontal: "left",
                                }
                            }}
                            labelId="freetimeActivity-label"
                            id="freetimeActivity"
                            name="freetimeActivity"
                            error={this.state.freetimeActivity || this.errors.freetimeActivity ? false:true}
                            value={this.state.freetimeActivity}
                            onChange={this.handleChange}>
                            <MenuItem value={1}>Sedentary</MenuItem>
                            <MenuItem value={2}>Semi-active</MenuItem>
                            <MenuItem value={3}>Active</MenuItem>
                        </Select>
                    </FormControl>
                    <FormControl className="pb-2" component="fieldset">
                        <FormLabel component="legend">Do you practice any sport?</FormLabel>
                        <RadioGroup
                            aria-label="practicesSport"
                            id="practicesSport"
                            name="practicesSport"
                            error={this.state.practicesSport || this.errors.practicesSport ? false:true}
                            value={this.state.practicesSport}
                            onChange={this.handleChange}>
                            <FormControlLabel value="1" control={<PurpleRadio />} label="Yes" />
                            <FormControlLabel value="2" control={<PurpleRadio />} label="No" />
                        </RadioGroup>
                    </FormControl>
                    <TextField onChange={this.handleChange} value={this.state.timpMediuActivitate}
                               error={this.state.timpMediuActivitate || this.errors.timpMediuActivitate ? false:true}
                               className="pb-2" required name="timpMediuActivitate" id="timpMediuActivitate" label="Minutes of exercise per day"/>
                    <TextField onChange={this.handleChange} value={this.state.timpCalculator}
                               error={this.state.timpCalculator || this.errors.timpCalculator ? false:true}
                               className="pb-2" required name="timpCalculator" id="timpCalculator" label="Screen time per day (hours)"/>
                    <Button variant="outlined" color="primary" onClick={this.onSubmit}>Submit</Button>
                </form>
            </div>
        )
    }
}
