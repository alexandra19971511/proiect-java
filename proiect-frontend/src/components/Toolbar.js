import { AppBar, Tab, Tabs, withStyles, Tooltip } from '@material-ui/core';
import PowerSettingsNewIcon from '@material-ui/icons/PowerSettingsNew';
import React from 'react';
import { withRouter } from 'react-router-dom';
import {
  ADMIN_ROLE,
  ADMIN_ROUTE,
  DASHBOARD_ROUTE,
  FORMS_ROUTE,
  ROOT_ROUTE,
  USER_ROLE,
  INFO_ROUTE,
} from '../constants';
import { styles } from './Toolbar.scss';

const TabNames = {
  Info: {
    name: 'info',
    index: 0,
    label: 'Info',
    path: INFO_ROUTE,
  },
  Forms: {
    name: 'forms',
    index: 1,
    label: 'Quiz',
    path: FORMS_ROUTE
  },
  Dashboard: {
    name: 'dashboard',
    index: 2,
    label: 'Dashboard',
    path: DASHBOARD_ROUTE
  },
  Admin: {
    name: 'admin',
    index: 3,
    label: 'Admin',
    path: ADMIN_ROUTE
  },
};

const getTabObject = (predicate) => {
  return Object.values(TabNames).find(predicate) || {};
};

const getTabObjectByName = (name) => {
  return getTabObject(tab => tab.name === name)
};

const getTabObjectByIndex = (index) => {
  return getTabObject(tab => tab.index === index)
};

const getCurrentTabFromURL = () => {
  const tabName = (window.location.pathname.split('/').reverse() || [])[0] || '';
  return getTabObjectByName(tabName).index
};

class Toolbar extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      currentTab: getCurrentTabFromURL() || 0,
    };
  }

  onTabChange = (event, currentTab) => {
    this.setState({ currentTab });
    this.props.history.push(
      getTabObjectByIndex(currentTab).path
    );
  };

  handleLogout = (event) => {
    this.props.history.push(ROOT_ROUTE);
    localStorage.clear();
  };

  renderLogoutBtn = () => {
    return (
      <Tooltip title="Logout">
        <PowerSettingsNewIcon />
      </Tooltip>
    );
  };

  render = () => {
    const { classes } = this.props;
    const { currentTab } = this.state;

    return (
      <>
        <AppBar
          position="static"
          className={classes.toolbarContainer}
        >
          <Tabs
            value={currentTab}
            onChange={this.onTabChange}
          >
            <Tab label={TabNames.Info.label} />
            <Tab label={TabNames.Forms.label} />

            {ADMIN_ROLE === localStorage.getItem(USER_ROLE) &&
              (<Tab label={TabNames.Dashboard.label} />)
            }

            {ADMIN_ROLE === localStorage.getItem(USER_ROLE) &&
              (<Tab label={TabNames.Admin.label} />)
            }

            <Tab
              classes={{
                root: classes.logoutBtn,
              }}
              icon={this.renderLogoutBtn()}
              aria-label="logout"
              onClick={this.handleLogout}
            />
          </Tabs>
        </AppBar >

      </>
    );
  };
}

export default withRouter(withStyles(styles)(Toolbar));