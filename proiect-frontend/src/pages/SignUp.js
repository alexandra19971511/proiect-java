import { withStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';
import React from 'react';
import { withRouter } from 'react-router-dom';

import AuthenticationForm from '../components/AuthenticationForm/AuthenticationForm';
import {
  axios,
  SERVER_BASE_URL,
  LOGIN_ROUTE
} from '../constants';
import { styles } from './SignUp.css';

class SignUp extends React.Component {
  state = {
    errorMessage: undefined,
  }

  handleServerRequest = async (values) => {

    try {
      await axios.post(`${SERVER_BASE_URL}/register`, {
        fullName: `${values.firstName}${values.lastName}`,
        email: values.email,
        password: values.password
      })
      this.props.history.push(LOGIN_ROUTE);
    } catch (error) {
      this.setState({ errorMessage: 'Registration failed' });
    }
  };


  render() {
    const { classes } = this.props;
    const { errorMessage } = this.state;
    return (
      <div className={classes.root}>
        <Typography component="h1" variant="h3" color="inherit" gutterBottom>
          Sign up
        </Typography>

        <div className={classes.formInputs}>
          <AuthenticationForm
            isLogin={false}
            errorMessage={errorMessage}
            onSubmit={this.handleServerRequest} />
        </div>

      </div>
    );


  }
}

SignUp.propTypes = {

};

export default withRouter(withStyles(styles)(SignUp));