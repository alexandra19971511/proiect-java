import { withStyles } from '@material-ui/core/styles';
import PropTypes from 'prop-types';
import React from 'react';
import { withRouter } from 'react-router-dom';
import InputFormComponent from '../../components/InputFormComponent';
import Button from '@material-ui/core/Button';
import instance from '../../axios';
import { styles } from './AdminPage.css';

class AdminPage extends React.Component {
  state = {
    selectedProfessor: null, 
    selectedCourse: null,
    mean: 0,
    quizCount: 0,
    hasAnswer: false
  }

  handleSubmitButton = async () => {
    const { selectedProfessor, selectedCourse } = this.state;
    const { data: result } = await instance.get(`/answer/mean/${selectedProfessor.id}/${selectedCourse.id}`);
    this.setState({ mean: result.mean, quizCount: result.quizCount, hasAnswer: true });
  };

  handleDisabledButton = () => {
    return !this.state.selectedProfessor || !this.state.selectedCourse ;
  }

  onOptionSelect = ({ selectedProfessor, selectedCourse }) => {
    this.setState({ selectedProfessor, selectedCourse });
  };

  render = () => {
    const { mean, quizCount, hasAnswer } = this.state;
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

        {hasAnswer &&
          <p>
            A obtinut media {mean} in urma a {quizCount} de chestionare.
          </p>
        }
      </>
    );
  };

}

AdminPage.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withRouter(withStyles(styles)(AdminPage));