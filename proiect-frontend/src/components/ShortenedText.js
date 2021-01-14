import React from 'react';
import PropTypes from 'prop-types';
import Button from '@material-ui/core/Button';

export class ShortenedText extends React.Component {
    state = { expanded: false };

    expandText = () => {
        this.setState({ expanded: true });
    };

    renderExpandBtn = () => {
        return (
            <Button 
                variant="text"
                color="primary"
                size="small"
                onClick={this.expandText}
            >
                Continue reading
            </Button>
        );
    };

    render = () => {
        const { text, maxLength } = this.props;
        const { expanded } = this.state;

        if (expanded) return text;

        return text.length > maxLength 
            ? <span>{text.substring(0, maxLength)}...{this.renderExpandBtn()}</span>
            : text;
    };
}

ShortenedText.propTypes = {
    text: PropTypes.string.isRequired,
    maxLength: PropTypes.number.isRequired,
};