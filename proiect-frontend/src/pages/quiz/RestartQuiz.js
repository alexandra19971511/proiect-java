import Button from '@material-ui/core/Button';
import React from 'react';
import { withRouter } from 'react-router-dom';
import { FORMS_ROUTE } from '../../constants';

class RestartQuiz extends React.Component {
  handleSubmitButton = () => {
    this.props.history.push(FORMS_ROUTE);
  };

  render = () => {
    return (
      <>
        <Button
          variant="contained"
          color="primary"
          onClick={this.handleSubmitButton}
        >
          Retake Quiz
        </Button>
      </>
    );
  };
}


export default withRouter(RestartQuiz);