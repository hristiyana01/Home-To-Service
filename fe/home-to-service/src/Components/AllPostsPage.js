// // src/Components/AllPostsPage.js

// import React, { useEffect, useState } from 'react';
// import axios from "axios";
// import { Link } from 'react-router-dom';
// import './PostsForCategory.css'; // Import the CSS file

// function AllPostsPage() {
//   const [posts, setPosts] = useState([]);

//   useEffect(() => {
//     const fetchPosts = async () => {
//       try {
//         const response = await axios.get(`http://localhost:8080/post/all`);
//         setPosts(response.data);
//       } catch (error) {
//         console.error('There was an error!', error);
//       }
//     };

//     fetchPosts();
//   }, []);
//   {posts.map(post => (
//     <Link to={`/post/${post.id}`} key={post.id}>
//       <h2>{post.title}</h2>
//       <p>{post.body}</p>
//     </Link>
//   ))}

//   return (
//     <div  className="category-posts-main">
//       <h1>All Posts</h1>
//       {posts.map(post => {
//         const date = new Date(post.createdDate);
//         const formattedDate = date.toLocaleDateString();
//         const formattedTime = date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });

//         return (
//         //   <div key={post.id} className="post">
//         //     <img className="post-image" src="https://dummyimage.com/80x80/dee2e6/6c757d.jpg" alt={post.title} />
//         //     <div className="post-details">
//         //       <h2>{post.title}</h2>
//         //       <p>{post.location}</p>
//         //       <p>{`${formattedDate}, ${formattedTime}`}</p>
//         //     </div>
//         //     <p className="post-price">{post.price}</p>
//         //   </div>
//         <Link to={`/post/${post.id}`} key={post.id} className="post-link">
//         <div className="post">
//           <img className="post-image" src="https://dummyimage.com/80x80/dee2e6/6c757d.jpg" alt={post.title} />
//           <div className="post-details">
//             <h2>{post.title}</h2>
//             <p>{post.location}</p>
//             <p>{`${formattedDate}, ${formattedTime}`}</p>
//           </div>
//           <p className="post-price">{post.price} BGN</p>
//         </div>
//       </Link>
//         );
//       })}
//     </div>
//   );
// }

// export default AllPostsPage;
// src/Components/AllPostsPage.js

import React, { useEffect, useState } from 'react';
import axios from "axios";
import { Link } from 'react-router-dom';
import './AllPostsPage.css'; // Import the new CSS file

function AllPostsPage() {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await axios.get('http://localhost:8080/post/all');
        setPosts(response.data);
      } catch (error) {
        console.error('There was an error!', error);
      }
    };

    fetchPosts();
  }, []);

  return (
    <div className="category-posts-main">
      <h1>All Posts</h1>
      <div className="post-grid">
        {posts.map(post => {
          const date = new Date(post.createdDate);
          const formattedDate = date.toLocaleDateString();
          const formattedTime = date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });

          return (
            <Link to={`/posts/${post.id}`} key={post.id} className="post-link">
              <div className="post">
                <img className="post-image" src="https://dummyimage.com/80x80/dee2e6/6c757d.jpg" alt={post.title} />
                <div className="post-details">
                  <h2>{post.title}</h2>
                  <p>{post.location}</p>
                  <p>{`${formattedDate}, ${formattedTime}`}</p>
                </div>
                <p className="post-price">{post.price} BGN</p>
              </div>
            </Link>
          );
        })}
      </div>
    </div>
  );
}

export default AllPostsPage;
