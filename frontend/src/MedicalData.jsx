import React from "react";
import {api_url} from "./helpers";
import TableContainer from "@material-ui/core/TableContainer";
import Table from "@material-ui/core/Table";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import TableBody from "@material-ui/core/TableBody";
import {colors} from "@material-ui/core";
import red from "@material-ui/core/colors/red";
import CardContent from "@material-ui/core/CardContent";
import Typography from "@material-ui/core/Typography";
import Card from "@material-ui/core/Card";


export class MedicalData extends React.Component{


    constructor(props){
        super(props);
        this.state = {
            analize: [],
            programari: []
        };
        this.getMedicalData = this.getMedicalData.bind(this);
        this.getMedicaHistory = this.getMedicaHistory.bind(this);
        this.getFirstRecommendedVisit = this.getFirstRecommendedVisit.bind(this);
        this.getMessage = this.getMessage.bind(this);
    }

    getMedicalData = async () => {
        var response = await fetch(api_url + '/getAllAnalize/');
        var data = await response.json();
        return data;
    };

    getMedicaHistory = async () => {
        var response = await fetch(api_url + '/getAllHistory/');
        var data = await response.json();
        return data;
    };

    componentDidMount() {
        this.getMedicalData().then(
            data => {
                this.setState({analize: data});
            });
        this.getMedicaHistory().then(
            data => {
                this.setState({programari: data});
            });
    }

    getFirstRecommendedVisit = function(){
        var today = new Date();
        var max = this.state.programari.length ? new Date(this.state.programari[0].recommendVisit) : new Date();
        var name = '';
        this.state.programari.forEach(programare => {
            var recommendedDate = new Date(programare.recommendVisit);
            if(recommendedDate > today){
                if(recommendedDate < max){
                    max = recommendedDate;
                    name= programare.name;
                }
            }
        });
        return {name: name,
                date: max};
    };

    getMessage = function(){
        var programare = this.getFirstRecommendedVisit();
        return 'Next appointment to ' + programare.name + ', is on the: ' + programare.date.toLocaleDateString();
    };

    setValueColor = function(value, min, max){
        if(value < min || value > max){
            return 'red'
        }
        return 'green';
    };

    render() {
        return (
            <div className="content container">
                <div className={"title d-flex flex-column col-10 m-auto pt-4"}>
                    <h3>Medical Data</h3>
                    <TableContainer>
                        <Table aria-label="simple table">
                            <TableHead>
                                <TableRow>
                                    <TableCell align="left">Name</TableCell>
                                    <TableCell align="left">Value</TableCell>
                                    <TableCell align="left">Min. Value</TableCell>
                                    <TableCell align="left">Max. Value</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {this.state.analize.map((row) => (
                                    <TableRow key={row.name}>
                                        <TableCell component="th" scope="row">
                                            {row.name}
                                        </TableCell>
                                        <TableCell align="center"
                                                   style={{color: this.setValueColor(row.value, row.minValue, row.maxValue)}}>
                                            {row.value}</TableCell>
                                        <TableCell align="center">{row.minValue}</TableCell>
                                        <TableCell align="center">{row.maxValue}</TableCell>
                                    </TableRow>
                                ))}
                            </TableBody>
                        </Table>
                    </TableContainer>
                    <div className='water_intake'>
                        <Card>
                            <CardContent>
                                <Typography  color="textSecondary" gutterBottom>
                                    Next Appointment
                                </Typography>
                                <Typography variant="h5" component="h2">
                                    {this.getMessage()}
                                </Typography>
                            </CardContent>
                        </Card>
                    </div>

                </div>
            </div>
        )
    }
}
