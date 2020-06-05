import React from 'react';
import Button from '@material-ui/core/Button';
import ClickAwayListener from '@material-ui/core/ClickAwayListener';
import Grow from '@material-ui/core/Grow';
import Paper from '@material-ui/core/Paper';
import Popper from '@material-ui/core/Popper';
import MenuItem from '@material-ui/core/MenuItem';
import MenuList from '@material-ui/core/MenuList';
import {createMuiTheme, makeStyles, MuiThemeProvider} from '@material-ui/core/styles';
import MenuIcon from '@material-ui/icons/Menu';
import './App.css';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";
import {Lifestyle} from "./Lifestyle";
import {MedicalHistory} from "./MedicalHistory";
import {MedicalTest} from "./MedicalTest";
import AppBar from "@material-ui/core/AppBar/AppBar";
import AccountBoxIcon from '@material-ui/icons/AccountBox';
import purple from "@material-ui/core/colors/purple";
import Home from "./Home";
import Menu from '@material-ui/core/Menu';
import Select from "@material-ui/core/Select/Select";
import {Activity} from "./Activity";
import {Progress} from "./Progress";
import {MedicalData} from "./MedicalData";

const useStyles = makeStyles((theme) => ({
    root: {
        display: 'flex',
    },
    paper: {
        marginRight: theme.spacing(0),
    },
}));

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

export default function MenuListComposition() {
    const classes = useStyles();
    const [open, setOpen] = React.useState(false);
    const anchorRef = React.useRef(null);

    const handleToggle = () => {
        setOpen((prevOpen) => !prevOpen);
    };

    const handleClose = (event) => {
        if (anchorRef.current && anchorRef.current.contains(event.target)) {
            return;
        }

        setOpen(false);
    };

    function handleListKeyDown(event) {
        if (event.key === 'Tab') {
            event.preventDefault();
            setOpen(false);
        }
    }

    // return focus to the button when we transitioned from !open -> open
    const prevOpen = React.useRef(open);
    React.useEffect(() => {
        if (prevOpen.current === true && open === false) {
            anchorRef.current.focus();
        }

        prevOpen.current = open;
    }, [open]);

    const [anchorEl, setAnchorEl] = React.useState(null);

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose2 = () => {
        setAnchorEl(null);
    };

    return (
        <Router>
            <MuiThemeProvider theme={theme}>
                <div className="App">
                    <AppBar color="secondary" className="App-header">
                        <div className="header d-flex row justify-content-between align-items-center">
                            <div className="menu">
                                <div className={classes.root}>
                                    <div>
                                        <Button
                                            ref={anchorRef}
                                            aria-controls={open ? 'menu-list-grow' : undefined}
                                            aria-haspopup="true"
                                            onClick={handleToggle}
                                        >
                                            <MenuIcon/>
                                        </Button>
                                        <Popper  className={"menu-open"} open={open} anchorEl={anchorRef.current} role={undefined} transition disablePortal>
                                            {({ TransitionProps, placement }) => (
                                                <Grow
                                                    {...TransitionProps}
                                                    style={{ transformOrigin: placement === 'bottom' ? 'center top' : 'center bottom' }}
                                                >
                                                    <Paper>
                                                        <ClickAwayListener onClickAway={handleClose}>
                                                            <MenuList autoFocusItem={open} id="menu-list-grow" onKeyDown={handleListKeyDown}>
                                                                    <Link to="/medical-test">
                                                                        <MenuItem onClick={handleClose}>Medical Tests</MenuItem>
                                                                    </Link>
                                                                    <Link to="/medical-history">
                                                                        <MenuItem onClick={handleClose}>Medical History</MenuItem>
                                                                    </Link>
                                                                    <Link to="/lifestyle">
                                                                        <MenuItem onClick={handleClose}>Lifestyle</MenuItem>
                                                                    </Link>
                                                                    <Link to="/activity">
                                                                        <MenuItem onClick={handleClose}>Activity</MenuItem>
                                                                    </Link>
                                                                    <Link to="/progress">
                                                                        <MenuItem onClick={handleClose}>Progress</MenuItem>
                                                                    </Link>
                                                                    <Link to="/medicalData">
                                                                        <MenuItem onClick={handleClose}>Medical Data</MenuItem>
                                                                    </Link>
                                                            </MenuList>
                                                        </ClickAwayListener>
                                                    </Paper>
                                                </Grow>
                                            )}
                                        </Popper>
                                    </div>
                                </div>
                            </div>
                            <div className="logo pr-3">
                                <Link to="/">
                                    <MenuItem onClick={handleClose}>Healthy</MenuItem>
                                </Link>
                            </div>
                            <div className="user-menu pr-3 pt-2">
                                <div aria-controls="simple-menu" aria-haspopup="true" onClick={handleClick}>
                                    <AccountBoxIcon/>
                                </div>
                                <Menu
                                    id="simple-menu"
                                    anchorEl={anchorEl}
                                    keepMounted
                                    open={Boolean(anchorEl)}
                                    onClose={handleClose2}
                                >
                                    <MenuItem onClick={handleClose2}>Sign In</MenuItem>
                                    <MenuItem onClick={handleClose2}>Sign Up</MenuItem>
                                </Menu>
                            </div>
                        </div>
                    </AppBar>
                    <Switch>
                        <Route path="/medical-test">
                            <MedicalTest />
                        </Route>
                        <Route path="/medical-history">
                            <MedicalHistory />
                        </Route>
                        <Route path="/lifestyle">
                            <Lifestyle />
                        </Route>
                        <Route path="/Activity">
                            <Activity />
                        </Route>
                        <Route path="/progress">
                            <Progress />
                        </Route>
                        <Route path="/medicalData">
                            <MedicalData />
                        </Route>
                        <Route path="/">
                            <Home />
                        </Route>
                    </Switch>
                </div>
            </MuiThemeProvider>
        </Router>
    );
}
