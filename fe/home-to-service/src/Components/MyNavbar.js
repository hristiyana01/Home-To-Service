import { Navbar, Nav, Container } from 'react-bootstrap';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

//
// function PostDetails() {
//   const { id } = useParams();
//   const [post, setPost] = useState(null);
//   const [comments, setComments] = useState([]);
//
//   useEffect(() => {
//     // Fetch post details
//     fetch(`/api/posts/${id}`)
//       .then(response => response.json())
//       .then(data => setPost(data));
//
//     // Fetch comments
//     fetch(`/api/posts/${id}/comments`)
//       .then(response => response.json())
//       .then(data => setComments(data));
//   }, [id]);
//
//   if (!post) return 'Loading...';
//
//   return (
//     <div>
//       <h1>{post.title}</h1>
//       <p>{post.body}</p>
//       <h2>Comments</h2>
//       {comments.map(comment => (
//         <div key={comment.id}>
//           <p>{comment.body}</p>
//         </div>
//       ))}
//     </div>
//   );
// }

function MyNavbar() {

  return (
    <Navbar bg="light" expand="lg">
      <Container>
        <Navbar.Brand href="index.html">Home</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            {/*<Nav.Link href="/categories-list" component={CategoriesList}>Categories</Nav.Link>*/}
            <Nav.Link href="/all-posts">All Posts</Nav.Link>
            <Nav.Link href="/categories-list">Categories</Nav.Link>
            <Nav.Link href="#about">About</Nav.Link>
            <Nav.Link href="/contacts">Contact</Nav.Link>
          </Nav>
          <Nav className="right-side-navbar">
            <Nav.Link className="create-post-link" href="/post/create">Create Post</Nav.Link>
            <Nav.Link href="/login">Login</Nav.Link>
            <Nav.Link href="/register">Register</Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default MyNavbar;