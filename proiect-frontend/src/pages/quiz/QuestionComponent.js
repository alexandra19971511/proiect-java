import Typography from '@material-ui/core/Typography';
import FormControl from '@material-ui/core/FormControl';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import PropTypes from 'prop-types';
import React from 'react';
import { withStyles } from '@material-ui/core';

class QuestionComponent extends React.Component {
  onChange = (event, value) => this.props.onChange(value);

  render() {
    const { classes, question, value } = this.props;
    const id = question['id'];
    return (
      <div key={id}>
        <Typography component="h1">
          {question['question']}
        </Typography>

        <FormControl component="fieldset" className={`form-control-${id}`}>
          <RadioGroup
            aria-label={`radio-group-${id}`} 
            name={`radio-group-${id}`} 
            value={value.toString()}
            onChange={this.onChange}
            classes={{
              root: classes.radioButtonsContainer,
            }}
          >
            <FormControlLabel value="1" control={<Radio />} label="1" />
            <FormControlLabel value="2" control={<Radio />} label="2" />
            <FormControlLabel value="3" control={<Radio />} label="3" />
            <FormControlLabel value="4" control={<Radio />} label="4" />
            <FormControlLabel value="5" control={<Radio />} label="5" />
          </RadioGroup>
        </FormControl>
        <br></br>
      </div >
    );
  }
}

QuestionComponent.propTypes = {
  question: PropTypes.object.isRequired,
  value: PropTypes.number.isRequired,
  onChange: PropTypes.func.isRequired,
};

export default withStyles({
  radioButtonsContainer: {
    flexDirection: 'row',
  },
})(QuestionComponent);