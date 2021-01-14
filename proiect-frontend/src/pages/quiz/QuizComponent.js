import { Typography, Button } from '@material-ui/core';
import React from 'react';

import {
  RESTART_QUIZ_ROUTE,
  USER_ID,
  SELECTED_PROFESSOR_ID,
  SELECTED_COURSE_ID,
} from '../../constants';
import QuestionComponent from './QuestionComponent';
import instance from '../../axios';

const DEFAULT_VALUE = 0;

class QuizComponent extends React.Component {
  state = {
    questions: [],
    canShow: false,
    disabledSubmitButton: true,
  };

  componentDidMount = async () => {
    const { data: questions } = await instance.get('/question');
    this.setState({
      questions: questions.map((item) => ({ id: item.id, question: item.question }))
    });
    questions.forEach(({ id }) => {
      this.setState({ [id]: DEFAULT_VALUE });
    });
    this.setState({ canShow: true });
  };

  getQuestionAnswers = () => {
    const { questions, canShow, disabledSubmitButton, ...answers } = this.state;
    return answers;
  };

  didAnswerAllQuestions = (answers) => {
    const { questions } = this.state;
    return Object.values(answers).filter(value => {
      return value !== 0;
    }).length === questions.length;
  };

  handleOnChange = (questionId) => (value) => {
    const newAnswers = { ...this.getQuestionAnswers(), [questionId]: parseInt(value) };

    if (this.didAnswerAllQuestions(newAnswers)) {
      this.setState({ disabledSubmitButton: false });
    }

    this.setState({ ...newAnswers });
  };

  onClick = async () => {
    const { questions, canShow, disabledSubmitButton, ...answers } = this.state;
    const answerObj = { 'answers': answers };
    const userId = parseInt(localStorage.getItem(USER_ID));
    const professorId = parseInt(localStorage.getItem(SELECTED_PROFESSOR_ID));
    const courseId = parseInt(localStorage.getItem(SELECTED_COURSE_ID));
    await instance.post(`/answer/${userId}/${professorId}/${courseId}`, answerObj);
    this.props.history.push(RESTART_QUIZ_ROUTE);
  };

  render() {
    if (!this.state.canShow) {
      return null;
    }

    return (
      <div >
        <Typography component="h2" >
          Notarea se va face de la 1 la 5
          </Typography>
          <br></br>
        <div>
          {this.state.questions.map((question) =>
            (
              <QuestionComponent
                key={question.id}
                question={question}
                value={this.state[question.id]}
                onChange={this.handleOnChange(question.id)}
              >
              </QuestionComponent>
            ))}
        </div>
        <Button
          variant="contained"
          color="primary"
          disabled={this.state.disabledSubmitButton}
          onClick={this.onClick}
        >
          Finish quiz
        </Button>
      </div >
    );
  }
}

export default QuizComponent;