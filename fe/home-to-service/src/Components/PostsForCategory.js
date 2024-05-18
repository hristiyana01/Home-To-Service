// src/Components/PostsForCategory.js

import React, { useEffect, useState } from 'react';
import axios from "axios";
import { useParams } from 'react-router-dom';

function PostsForCategory() {
  const [posts, setPosts] = useState([]);
  const { id } = useParams();

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const responseCategories = await axios.get(`http://localhost:8080/post/all/${id}`);
        //todo implement category name instead of id
        // style the posts
        // hover links remove annoying blue color
        const response = await axios.get(`http://localhost:8080/post/all/${id}`);
        setPosts(response.data);
      } catch (error) {
        console.error('There was an error!', error);
      }
    };

    fetchPosts();
  }, [id]);

  return (
    <div>
      <h1>Posts for Category {id}</h1>
      {posts.map(post => (
        <div key={post.id}>
          <h2>{post.title}</h2>
          <p>{post.content}</p>
          <p>{post.location}</p>
          <p>{post.price}</p>

        </div>
      ))}
    </div>
  );
}

export default PostsForCategory;