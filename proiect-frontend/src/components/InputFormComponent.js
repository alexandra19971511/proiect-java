import React from 'react';
import PropTypes from 'prop-types';
import { withRouter } from 'react-router-dom';

import Typeahead from './Typeahead';
import instance from '../axios';

class InputFormComponent extends React.Component {
  state = {
    professors: [],
    courses: [],
    selectedProfessor: undefined,
    selectedCourse: undefined,
  }

  componentDidMount = async() => {
    await instance.get('/professor')
      .then(response => {
        this.setState({ professors: response.data.map((item, i) => ({ name: item.fullName, id: item.id })) });
      })
      .catch(() => {});
  };

  handleSelectedProfessor = async (value) => {
    const { onOptionsSelect } = this.props;
    const newState = { ...this.state, selectedProfessor: value };

    this.setState(newState);
    if (value != null) {
      const { data: courses } = await instance.get(`/course/professor/${value.id}`);
      this.setState({ courses: courses });
    }
    onOptionsSelect(newState);
  };

  handleSelectedCourse = (value) => {
    const { onOptionsSelect } = this.props;
    const newState = { ...this.state, selectedCourse: value, disabledSubmitButton: false }
    this.setState(newState)
    onOptionsSelect(newState);
  };

  renderProfessorsInput = () => {
    return (
      <Typeahead
        id="search-professors"
        label="Search professor..."
        onOptionSelect={this.handleSelectedProfessor}
        options={this.state.professors}
      />
    );
  };

  renderCoursesInput = () => {
    return (
      <Typeahead
        id="search-courses"
        label="Search course..."
        onOptionSelect={this.handleSelectedCourse}
        options={this.state.courses}
      />
    );
  };

  render = () => {
    return (
      <>
        {this.renderProfessorsInput()}
        {this.renderCoursesInput()}
      </>
    );
  };
}

InputFormComponent.propTypes = {
  onOptionsSelect: PropTypes.func.isRequired,
}

export default withRouter(InputFormComponent);