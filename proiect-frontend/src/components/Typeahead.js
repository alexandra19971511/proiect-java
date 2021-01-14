import React from 'react';
import * as PropTypes from 'prop-types';
import TextField from '@material-ui/core/TextField';
import Autocomplete from '@material-ui/lab/Autocomplete';
import CircularProgress from '@material-ui/core/CircularProgress';
import { withStyles } from '@material-ui/core/styles';

import { styles } from './Typeahead.scss';

class Typeahead extends React.Component {
  state = {
    loading: false,
    open: false,
    options: [],
    currentValue: '',
  };

  setOpen = (value) => () => {
    this.setState({ open: value });
  };

  onInputChanged = async (event) => {
    this.setState({ loading: true });
    // console.log(event);
    await this.props.onInputChanged(event);
    this.setState({ loading: false });
  };

  onOptionSelect = (event, value) => {
    this.setState({ currentValue: value })
    this.props.onOptionSelect(value);
  };

  render = () => {
    const { loading, open } = this.state;
    const { id, options, label, classes } = this.props;

    return (
      <Autocomplete
        onChange={this.onOptionSelect}
        className={classes.container}
        id={id}
        open={open}
        onOpen={this.setOpen(true)}
        onClose={this.setOpen(false)}
        getOptionSelected={(option, value) => option.name === value.name}
        getOptionLabel={option => option.name}
        options={options}
        loading={loading}
        renderInput={params => {
          // Hack because the component is not working proeprly
          // @see: https://stackoverflow.com/questions/58684579/handle-change-on-autocomplete-component-from-material-ui
          // params.inputProps.value = currentValue ? currentValue.name : '';
          return (
            <TextField
              {...params}
              label={label}
              fullWidth
              variant="outlined"
              InputProps={{
                ...params.InputProps,
                endAdornment: (
                  <React.Fragment>
                    {loading ? <CircularProgress color="inherit" size={20}/> : null}
                    {params.InputProps.endAdornment}
                  </React.Fragment>
                ),
              }}
            />
          );
        }}
      />
    );
  };
}

Typeahead.propTypes = {
  id: PropTypes.string.isRequired,
  label: PropTypes.string.isRequired,
  onInputChanged: PropTypes.func,
  onOptionSelect: PropTypes.func.isRequired,
  options: PropTypes.arrayOf(
    PropTypes.shape({
      name: PropTypes.string.isRequired,
      id: PropTypes.number.isRequired,
    }),
  ),
};

export default withStyles(styles)(Typeahead);
