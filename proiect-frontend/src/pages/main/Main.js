import React from 'react';
import { Switch, Route, Redirect } from 'react-router-dom';
import { Container } from '@material-ui/core';

import {
  DASHBOARD_ROUTE,
  FORMS_ROUTE,
  QUIZ_ROUTE,
  RESTART_QUIZ_ROUTE,
  ADMIN_ROUTE,
  INFO_ROUTE,
  IS_LOGGED_IN
} from '../../constants';
import Toolbar from '../../components/Toolbar';
import Info from '../Info';
import FormsComponent from '../quiz/FormsComponent';
import DashboardPage from '../dashboard/DashboardPage'
import QuizComponent from '../quiz/QuizComponent';
import RestartQuiz from '../quiz/RestartQuiz';
import AdminPage from '../admin/AdminPage'

class Main extends React.Component {
  state = {
    canRender: false
  };

  componentDidMount = async () => {
    const isLoggedIn = localStorage.getItem(IS_LOGGED_IN);
    this.setState({ canRender: isLoggedIn });
  }

  renderRoutes = () => {
    return (
      <Container>
        <Switch>
          <Route path={INFO_ROUTE} exact component={Info} />;
          <Route path={DASHBOARD_ROUTE} exact component={DashboardPage} />
          <Route path={FORMS_ROUTE} exact component={FormsComponent} />
          <Route path={QUIZ_ROUTE} exact component={QuizComponent} />
          <Route path={RESTART_QUIZ_ROUTE} exact component={RestartQuiz} />
          <Route path={ADMIN_ROUTE} exact component={AdminPage} />
          <Redirect to={INFO_ROUTE} />
        </Switch>
      </Container>
    );
  };

  render = () => {
    const { canRender } = this.state;

    if (!canRender) {
      return <span>Loading...</span>
    }

    return (
      <>
        <Toolbar />
        {this.renderRoutes()}
      </>
    );
  };
}

export default Main;