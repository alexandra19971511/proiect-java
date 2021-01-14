import { withStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';
import React from 'react';
import { withRouter } from 'react-router-dom';

import AuthenticationForm from '../components/AuthenticationForm/AuthenticationForm';
import {
  axios,
  MAIN_ROUTE,
  SERVER_BASE_URL,
  USER_EMAIL,
  TOKEN,
  USER_ID,
  USER_ROLE,
  IS_LOGGED_IN
} from '../constants';
import { styles } from './Login.css';
import instance from '../axios';

class Login extends React.Component {
  state = {
    errorMessage: undefined,
  }

  handleServerRequest = async (values) => {
    try {
      const response = await axios.post(`${SERVER_BASE_URL}/authenticate`, {
        email: values['email'],
        password: values['password']
      });

      localStorage.setItem(TOKEN, `Bearer ${response.data.jwtToken}`);
      const email = values['email']
      localStorage.setItem(USER_EMAIL, email);

      try {
        await this.handleUserInfo(email);
        this.props.history.push(MAIN_ROUTE);

      } catch (error) {
        console.log(error);
      }
    } catch (error) {
      this.setState({ errorMessage: 'Authentication failed! Username or password is not correct.' });
    }
  };

  handleUserInfo = async (email) => {
    const userObj = { email };

    if (userObj !== null) {
      const { data: user } = await instance.post(`/user-info`, userObj);
      if (user) {
        localStorage.setItem(USER_ID, user['id']);
        localStorage.setItem(USER_ROLE, user['role']);
        localStorage.setItem(IS_LOGGED_IN, true);
      }
    }
  };

  render() {
    const { classes } = this.props;
    const { errorMessage } = this.state;

    return (
      <div className={classes.root}>
        <Typography component="h1" variant="h3" color="inherit" gutterBottom>
          Login
        </Typography>

        <div className={classes.formInputs}>
          <AuthenticationForm
            isLogin={true}
            errorMessage={errorMessage}
            onSubmit={this.handleServerRequest}
          />
        </div>
      </div>
    );
  }
}

export default withRouter(
  withStyles(styles)(Login)
);