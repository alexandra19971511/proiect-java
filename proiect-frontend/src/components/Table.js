import React, { forwardRef } from 'react';
import PropTypes from 'prop-types';
import MaterialTable from 'material-table';
import { withStyles } from '@material-ui/core/styles';

import Search from '@material-ui/icons/Search';
import ViewColumn from '@material-ui/icons/ViewColumn';
import SaveAlt from '@material-ui/icons/SaveAlt';
import ChevronLeft from '@material-ui/icons/ChevronLeft';
import ChevronRight from '@material-ui/icons/ChevronRight';
import FirstPage from '@material-ui/icons/FirstPage';
import LastPage from '@material-ui/icons/LastPage';
import Add from '@material-ui/icons/Add';
import Check from '@material-ui/icons/Check';
import Clear from '@material-ui/icons/Clear';
import FilterList from '@material-ui/icons/FilterList';
import Remove from '@material-ui/icons/Remove';
import SortArrow from '@material-ui/icons/ArrowDownward';

import { styles } from './Table.css';

class Table extends React.Component {
  render() {
    const { columns, data, onAdd, onDelete } = this.props;

    // to add a drop down to t
    return (
      <MaterialTable
      icons={{
        Add: forwardRef((props, ref) => <Add {...props} ref={ref} />),
        SortArrow: forwardRef((props, ref) => <SortArrow  fontSize="inherit" {...props} ref={ref} />),
        Clear: forwardRef((props, ref) => <Clear {...props} ref={ref} />),
        Check: forwardRef((props, ref) => <Check {...props} ref={ref} />),
        Export: forwardRef((props, ref) => <SaveAlt {...props} ref={ref} />),
        Filter: forwardRef((props, ref) => <FilterList {...props} ref={ref} />),
        FirstPage: forwardRef((props, ref) => <FirstPage {...props} ref={ref} />),
        LastPage: forwardRef((props, ref) => <LastPage {...props} ref={ref} />),
        NextPage:  forwardRef((props, ref) => <ChevronRight {...props} ref={ref} />),
        PreviousPage: forwardRef((props, ref) => <ChevronLeft {...props} ref={ref} />),
        Search: forwardRef((props, ref) => <Search {...props} ref={ref} />),
        ResetSearch:  forwardRef((props, ref) => <Clear {...props} ref={ref} />),
        Delete: forwardRef((props, ref) => <Remove  fontSize="small" {...props} ref={ref} />),
        ViewColumn: forwardRef((props, ref) => <ViewColumn {...props} ref={ref} />),
        DetailPanel: forwardRef((props, ref) => <ChevronRight {...props} ref={ref} />),
      }}
        title={`Dashboard`}
        columns={columns}
        data={data}
        editable={{
          onRowAdd: newRow => onAdd(newRow),
    
          onRowDelete: row => onDelete(row),
        }}
      />
    );
  }

}

Table.propTypes = {
  classes: PropTypes.object.isRequired,
  columns: PropTypes.array.isRequired,
  data: PropTypes.array.isRequired
};

export default withStyles(styles)(Table);