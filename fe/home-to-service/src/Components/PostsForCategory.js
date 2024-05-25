import React, {useContext, useEffect, useState} from 'react';
import axios from "axios";
import { useParams } from 'react-router-dom';
import PostOverview from "./PostOverview";
import {UserContext} from "./App";

function PostsForCategory() {
  const [posts, setPosts] = useState([]);
  const { categoryId } = useParams();
  const { user } = useContext(UserContext)
  console.log('haide usera mi');
  console.log(user);

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/post/all/${categoryId}`);
        setPosts(response.data);
      } catch (error) {
        console.error('There was an error!', error);
      }
    };

    fetchPosts();
  }, [categoryId]);


  return (
    <div className="category-posts-main">
      <h1>Posts for Category {categoryId}</h1>
      {posts.map(post => <PostOverview key={post.id} post={post} />)}

      {/*{posts.map(post =>*/}
      {/*  {*/}
      {/*    const date = new Date(post.createdDate);*/}
      {/*    const formattedDate = date.toLocaleDateString();*/}
      {/*    const formattedTime = date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });*/}
      {/*    return (*/}
      {/*    <div key={post.id} className="post">*/}
      {/*      <img className="post-image" src="https://dummyimage.com/150x150/dee2e6/6c757d.jpg" alt={post.title} />*/}
      {/*      <div className="post-details">*/}
      {/*        <h2>{post.title}</h2>*/}
      {/*        <p>{post.location}</p>*/}
      {/*        <p>{`${formattedDate}, ${formattedTime}`}</p>*/}
      {/*      </div>*/}
      {/*      <p className="post-price">{post.price} BGN</p>*/}
      {/*    </div>*/}
      {/*  );*/}
      {/*  }*/}
      {/*)}*/}
    </div>
  );
}

export default PostsForCategory;