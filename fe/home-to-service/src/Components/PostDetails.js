import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

function PostDetails() {
  const { id } = useParams();
  const [post, setPost] = useState(null);
  const [comments, setComments] = useState([]);
  const user = useContext(UserContext);


  useEffect(() => {
    // Fetch post details
    fetch(`/api/posts/${id}`)
      .then(response => response.json())
      .then(data => setPost(data));

    // Fetch comments
    fetch(`/api/posts/${id}/comments`)
      .then(response => response.json())
      .then(data => setComments(data));
  }, [id]);

  if (!post) return 'Loading...';

  return (
    <div>
      <h1>{post.title}</h1>
      <p>{post.body}</p>
      <h2>Comments</h2>
      {comments.map(comment => (
        <div key={comment.id}>
          <p>{comment.body}</p>
        </div>
      ))}
    </div>
  );
}
export default PostDetails;