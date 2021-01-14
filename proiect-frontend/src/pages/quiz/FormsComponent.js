import React from 'react';

import InputFormComponent from '../../components/InputFormComponent';
import { QUIZ_ROUTE } from '../../constants';
import Button from '@material-ui/core/Button';
import { withRouter } from 'react-router-dom';

class FormsComponent extends React.Component {
  state = {
    disabledSubmitButton: true,
  }

  handleSubmitButton = () => {
    const { selectedProfessor, selectedCourse } = this.state;
    localStorage.setItem("professorId", selectedProfessor.id);
    localStorage.setItem("courseId", selectedCourse.id);
    this.props.history.push(QUIZ_ROUTE);
  };

  handleDisabledButton = () => {
    return !this.state.selectedProfessor || !this.state.selectedCourse;
  }

  onOptionSelect = ({ selectedProfessor, selectedCourse }) => {
    this.setState({ selectedProfessor, selectedCourse });
  };

  render = () => {
    return (
      <>
        <InputFormComponent
          onOptionsSelect={this.onOptionSelect}
        />

        <Button
          variant="contained"
          color="primary"
          disabled={this.handleDisabledButton()}
          onClick={this.handleSubmitButton}
        >
          Submit
        </Button>
      </>
    );
  };
}


export default withRouter(FormsComponent);