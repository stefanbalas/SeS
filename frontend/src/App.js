import React from 'react';
import './App.css';
import { MuiThemeProvider, createMuiTheme } from "@material-ui/core/styles";
import {Form} from "./Form";
import MenuListComposition from "./Menu";
import purple from "@material-ui/core/colors/purple";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";

const theme = createMuiTheme({
    palette: {
        type: "dark",
        primary: purple,
        secondary: {
            main: '#1d1f27',
            contrastText: '#FFF',
        }
    }
});

function App() {
    return (
        <MuiThemeProvider theme={theme}>
            <div className="App">
                <AppBar color="secondary" className="App-header container">
                    <div className="header d-flex row justify-content-between align-items-center">
                        <div className="menu">
                            <MenuListComposition/>
                        </div>
                        <div className="logo pr-3">
                            Healthy
                        </div>
                        <div className="user-menu pr-3">
                            P
                        </div>
                    </div>
                </AppBar>
                <div className="content container">
                    <Form/>
                </div>
            </div>
        </MuiThemeProvider>
    );
}

export default App;
