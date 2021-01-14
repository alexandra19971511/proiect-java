export const styles = theme => ({
  toolbar: {
    borderBottom: `1px solid ${theme.palette.divider}`,
    display: 'flex',
    justifyContent: 'flex-end',
    marginBottom: theme.spacing(2),
    '& > button:not(:last-of-type)': {
      marginRight: theme.spacing(1),
    },
  },
  mainFeaturedPost: {
    position: 'relative',
    margin: theme.spacing(4),
  },
  mainFeaturedPostContent: {
    position: 'relative',
    padding: theme.spacing(3),
    [theme.breakpoints.up('md')]: {
      padding: theme.spacing(6),
      paddingRight: 0,
    },
  },
  paragraph: {
    textAlign: 'justify',
  },
  feedbackImg: {
    padding: theme.spacing(1),
    '& > img': {
      width: '70%',
    },
  },
});