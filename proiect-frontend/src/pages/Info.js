import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Toolbar from '@material-ui/core/Toolbar';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';
import Container from '@material-ui/core/Container';
import { withRouter } from 'react-router-dom';
import { Text } from '../assets/text';
import Feedback from '../assets/feedback.jpg';

import {
  LOGIN_ROUTE,
  SIGNUP_ROUTE,
  IS_LOGGED_IN,
} from '../constants';
import { ShortenedText } from '../components/ShortenedText';
import { styles } from './Info.css';

const defaultBtnProps = {
  variant: 'outlined',
  color: 'primary',
  size: 'small',
};

class Info extends React.Component {

  handleLogin = () => {
    this.props.history.push(LOGIN_ROUTE);
  };

  handleSignUp = () => {
    this.props.history.push(SIGNUP_ROUTE);
  };

  render() {
    const { classes } = this.props;
    const isLoggedIn = JSON.parse(localStorage.getItem(IS_LOGGED_IN));

    return (
      <React.Fragment>
        <Container maxWidth="lg">
          {!isLoggedIn &&
            <Toolbar className={classes.toolbar} color="primary">
              <Button
                {...defaultBtnProps}
                className={classes.button}
                onClick={this.handleLogin}
              >
                Login
            </Button>
              <Button
                {...defaultBtnProps}
                color="default"
                className={classes.button}
                onClick={this.handleSignUp}
              >
                Sign up
            </Button>
            </Toolbar>}
          <main>
            <Paper>

            </Paper>
            <Paper className={classes.mainFeaturedPost}>
              <Grid container>
                <Grid item lg={12}>
                  <div className={classes.mainFeaturedPostContent}>
                    <Typography variant="h4" color="inherit" paragraph>
                      Ce oferim:
                    </Typography>
                    <Typography variant="h5" color="inherit" paragraph className={classes.paragraph}>
                      <ShortenedText text={Text.Provide_feedback} maxLength={500} />
                    </Typography>
                    <Grid className={classes.feedbackImg}>
                      <img src={Feedback} alt="logo" />
                    </Grid>
                  </div>
                </Grid>
              </Grid>
            </Paper>
          </main>
        </Container>
      </React.Fragment>
    );
  }

}

Info.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withRouter(withStyles(styles)(Info));