import { withStyles } from '@material-ui/core/styles';
import PropTypes from 'prop-types';
import React from 'react';
import { withRouter } from 'react-router-dom';
import Table from '../../components/Table';
import instance from '../../axios';
import { styles } from './DashboardPage.css';

class DashboardPage extends React.Component {
  state = {
    professors: []
  }

  handleAdd = async (newProfessor) => {
    const { data: professorResponse } = await instance.post('/dashboard/professor', newProfessor)
    const { professors } = this.state;
    this.setState({ professors: [...professors, professorResponse] });
  };

  handleDelete = async (professor) => {
    const professorId = professor.id;
    const courseId = professor.courseDto.id;
    await instance.delete(`/dashboard/professor/${professorId}/course/${courseId}`);
    const { professors } = this.state;
    var newArray = professors.filter(currentProfessor =>
      !(
        (currentProfessor.id === professorId) &&
        (currentProfessor.courseDto.id === courseId)
      )
    );

    this.setState({ professors: newArray });
  };

  componentDidMount = () => {
    instance.get('/dashboard/professor')
      .then(response => {
        this.setState({ professors: response.data });
      })
      .catch(function (error) {
        console.log(error);

      });
  };

  render() {
    const { professors } = this.state;

    const columns = [
      { title: 'Professor Name', field: 'fullName' },
      { title: 'Course Title', field: 'courseDto.name' },
    ];

    return (
      <Table
        columns={columns}
        data={professors}
        onAdd={this.handleAdd}
        onDelete={this.handleDelete}
      
      />
    );
  }

}

DashboardPage.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withRouter(withStyles(styles)(DashboardPage));