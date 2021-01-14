import React from 'react';
import clsx from 'clsx';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import IconButton from '@material-ui/core/IconButton';
import Input from '@material-ui/core/Input';
import InputLabel from '@material-ui/core/InputLabel';
import InputAdornment from '@material-ui/core/InputAdornment';
import FormControl from '@material-ui/core/FormControl';
import Visibility from '@material-ui/icons/Visibility';
import VisibilityOff from '@material-ui/icons/VisibilityOff';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

import { styles } from './AuthenticationForm.scss';

class AuthenticationForm extends React.Component {
    state = {
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        showPassword: false
    };

    handleChange = prop => event => {
        this.setState({ [prop]: event.target.value });
    };

    handleClickShowPassword = () => {
        this.setState({ showPassword: !this.state.showPassword });
    };

    handleMouseDownPassword = event => {
        event.preventDefault();
    };

    handleOnSubmit = () => {
        this.props.onSubmit(this.state);
    };

    renderFirstNameInput = () => {
        const { classes } = this.props;
        return (
            <FormControl className={clsx(classes.margin, classes.textField)}>
                <InputLabel htmlFor="adornment">First Name</InputLabel>
                <Input
                    id="standard-first-name"
                    value={this.state.firstName}
                    onChange={this.handleChange('firstName')}
                />
            </FormControl>
        );
    };

    renderLastNameInput = () => {
        const { classes } = this.props;
        return (
            <FormControl className={clsx(classes.margin, classes.textField)}>
                <InputLabel htmlFor="adornment">Last Name</InputLabel>
                <Input
                    id="standard-last-name"
                    value={this.state.lastName}
                    onChange={this.handleChange('lastName')}
                />
            </FormControl>
        );
    };

    renderEmailInput = () => {
        const { classes } = this.props;
        return (
            <FormControl className={clsx(classes.margin, classes.textField)}>
                <InputLabel htmlFor="adornment">Email</InputLabel>
                <Input
                    id="standard-email"
                    value={this.state.email}
                    onChange={this.handleChange('email')}
                />
            </FormControl>
        );
    };

    renderPasswordInput = () => {
        const { classes, errorMessage } = this.props;
        return (
            <FormControl className={clsx(classes.margin, classes.textField)}>
                <InputLabel htmlFor="standard-adornment-password">Password</InputLabel>
                <Input
                    id="standard-adornment-password"
                    type={this.state.showPassword ? 'text' : 'password'}
                    value={this.state.password}
                    onChange={this.handleChange('password')}
                    endAdornment={
                        <InputAdornment position="end">
                            <IconButton
                                aria-label="toggle password visibility"
                                onClick={this.handleClickShowPassword}
                                onMouseDown={this.handleMouseDownPassword}
                            >
                                {this.state.showPassword ? <Visibility /> : <VisibilityOff />}
                            </IconButton>
                        </InputAdornment>
                    }
                />
                {errorMessage &&
                    <Typography color='secondary'>
                        {errorMessage}
                    </Typography>}
            </FormControl>
        );
    };

    renderButton = () => {
        const { isLogin } = this.props;
        return (
            <Button
                color='primary'
                variant='contained'
                onClick={this.handleOnSubmit}
            >
                {isLogin ? 'Login' : 'Register'}
            </Button>
        );
    };

    render = () => {
        const { isLogin } = this.props;
        return (
            <>
                {!isLogin && (
                    <>
                        {this.renderFirstNameInput()}
                        {this.renderLastNameInput()}
                    </>
                )}
                {this.renderEmailInput()}
                {this.renderPasswordInput()}
                {this.renderButton()}
            </>
        );
    };
}

AuthenticationForm.propTypes = {
    isLogin: PropTypes.bool.isRequired,
    onSubmit: PropTypes.func.isRequired,
};

export default withStyles(styles)(AuthenticationForm);