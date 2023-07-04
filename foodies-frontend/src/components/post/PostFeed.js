import "./PostFeed.css";

import { Delete, Send, ThumbUp } from "@mui/icons-material";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import MoreVertIcon from "@mui/icons-material/MoreVert";
import {
  Divider,
  FormControl,
  Grid,
  InputLabel,
  List,
  ListItemSecondaryAction,
  ListItemText,
  OutlinedInput,
} from "@mui/material";
import Avatar from "@mui/material/Avatar";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import CardHeader from "@mui/material/CardHeader";
import CardMedia from "@mui/material/CardMedia";
import Collapse from "@mui/material/Collapse";
import IconButton from "@mui/material/IconButton";
import InputAdornment from "@mui/material/InputAdornment";
import ListItem from "@mui/material/ListItem";
import Typography from "@mui/material/Typography";
import { red } from "@mui/material/colors";
import { styled } from "@mui/material/styles";
import * as React from "react";
import Swal from "sweetalert2";

const ExpandMore = styled((props) => {
  const { expand, ...other } = props;
  return <IconButton {...other} />;
})(({ theme, expand }) => ({
  transform: !expand ? "rotate(0deg)" : "rotate(180deg)",
  marginLeft: "auto",
  transition: theme.transitions.create("transform", {
    duration: theme.transitions.duration.shortest,
  }),
}));

export default function PostFeed(props) {
  const [postInfo, setPostInfo] = React.useState({ ...props.data });

  const [expanded, setExpanded] = React.useState(false);
  //Set all comments
  const [comments, setComments] = React.useState([]);

  const [values, setValues] = React.useState({
    comment: "",
  });

  React.useEffect(() => {
    getAllComments();
    setPostInfo(props.data);
    // console.log(props);
    return () => {};
  }, [props.data]);

  // Handle comment change
  const handleCommentChange = (prop) => (event) => {
    setValues({ ...values, [prop]: event.target.value });
  };

  const handleExpandClick = () => {
    setExpanded(!expanded);
  };
  const handleClickSend = (event) => {
    setValues({
      ...values,
      comments: values.comment,
    });
    addComment().then(() => {
      getAllComments();
    });
  };

  // Add user comment
  async function addComment(event) {
    const response = await fetch(
      `http://localhost:8082/api/v1/user/posts/${postInfo.id}/comments`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          comment: values.comment,
        }),
      }
    );
    const jsonData = await response.json();
    if (jsonData.error) {
      Swal.fire({
        title: "Error",
        text: "Cannot post comment",
        icon: "error",
        showConfirmButton: false,
        timer: 3000,
      });
      return false;
    }
  }

  // Get all comments
  async function getAllComments() {
    const response = await fetch(
      `http://localhost:8082/api/v1/user/posts/${postInfo.id}/comments`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
    const data = await response.json();
    setComments(data);
  }

  return (
    <Card sx={{ marginTop: "1em" }}>
      <CardHeader
        avatar={
          <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
            Avatar
          </Avatar>
        }
        action={
          <IconButton aria-label="settings">
            <MoreVertIcon />
          </IconButton>
        }
        title="Shrimp and Chorizo Paella"
      />
      <CardMedia
        component="img"
        height="300"
        image={`data:image/jpeg;base64,${props.data.image}`}
        alt="Paella dish"
      />
      <CardContent>
        {/* Post Description goes here */}
        <Typography variant="body2" color="text.secondary">
          {props.data.postDescription}
        </Typography>
      </CardContent>
      <CardActions disableSpacing>
        <IconButton aria-label="add to favorites">
          <ThumbUp />
        </IconButton>
        <ExpandMore
          expand={expanded}
          onClick={handleExpandClick}
          aria-expanded={expanded}
          aria-label="show more"
        >
          <ExpandMoreIcon />
        </ExpandMore>
      </CardActions>
      <Collapse in={expanded} timeout="auto" unmountOnExit>
        <CardContent>
          <Typography paragraph>Comments</Typography>
          <FormControl sx={{ m: 1, width: "100%" }} variant="outlined">
            <InputLabel>Comment</InputLabel>
            <OutlinedInput
              autoFocus
              margin="dense"
              id="comment"
              label="Comment"
              type="text"
              fullWidth
              multiline
              maxRows={4}
              variant="outlined"
              name="comment"
              required
              value={values.comment}
              onChange={handleCommentChange("comment")}
              endAdornment={
                <InputAdornment position="end">
                  <IconButton
                    onClick={(event) => handleClickSend(event)}
                    edge="end"
                  >
                    {<Send />}
                  </IconButton>
                </InputAdornment>
              }
            />
          </FormControl>

          {/* Show comments list */}
          {comments.length > 0 ? (
            <List>
              {Array.from(comments).map((data, index) => (
                <div key={index}>
                  <ListItem>
                    <Grid container>
                      <Grid item xs={12}>
                        <ListItemText primary={data.comment}></ListItemText>
                        <ListItemSecondaryAction>
                          <IconButton>
                            <Delete sx={{ color: red[500] }} />
                          </IconButton>
                        </ListItemSecondaryAction>
                      </Grid>
                    </Grid>
                  </ListItem>
                  <Divider />
                </div>
              ))}
            </List>
          ) : (
            <Typography paragraph sx={{ m: 1 }}>
              No comments yet.
            </Typography>
          )}
        </CardContent>
      </Collapse>
    </Card>
  );
}
