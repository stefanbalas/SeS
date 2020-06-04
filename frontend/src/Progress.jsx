import React from "react";
import {api_url} from "./helpers";
import { LineChart, Line, CartesianGrid, XAxis, YAxis } from 'recharts';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';


export class Progress extends React.Component{


    constructor(props){
        super(props);
        this.state = {
            activity: [],
            message: ''
        };
        this.getActivityData = this.getActivityData.bind(this);
        this.averageWaterIntake = this.averageWaterIntake.bind(this);
    }

    getActivityData = async () => {
        var response = await fetch(api_url + '/getAllActivity/');
        var data = await response.json();
        return data;
    };

    componentDidMount() {
        this.getActivityData().then(
            data => {
                this.setState({activity: data, message: this.averageWaterIntake(data)});
            });
    }

    averageWaterIntake = function(data){
        var average = 0;
        data.forEach(activity => {
            if(activity.water){
                average = average + parseInt(activity.water, 10);
            }
        });
        average = parseFloat(average/data.length).toFixed(2);
        var message = 'Your average water intake is: ' + average + '.';
        if(average >= 3){
           message = message + "Great job! Keep maintaining this level!";
        } else {
            message = message + 'You should drink more water!';
        }

        return message;
    };

    render() {
        return (
            <div className="content container">
                <div className={"title d-flex flex-column col-10 m-auto pt-4"}>
                    <h3>Progress</h3>
                    <br/>
                    <p>Weight Progress:</p>
                    <LineChart width={300} height={200} data={this.state.activity}>
                        <Line type="monotone" dataKey="weight" stroke="#8884d8" />
                        <CartesianGrid stroke="#ccc" />
                        <YAxis />
                    </LineChart>
                    <div className='water_intake'>
                        <Card>
                            <CardContent>
                                <Typography  color="textSecondary" gutterBottom>
                                   Water intake
                                </Typography>
                                <Typography variant="h5" component="h2">
                                    {this.state.message}
                                </Typography>
                            </CardContent>
                        </Card>
                    </div>
                </div>
            </div>
        )
    }
}
