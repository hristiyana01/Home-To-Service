import React, {useContext, useEffect, useState} from 'react';
import axios from "axios";
import { useParams } from 'react-router-dom';
import PostOverview from "./PostOverview";
import {UserContext} from "./App";

function PostsForCategory() {
  const [posts, setPosts] = useState([]);
  const { categoryId } = useParams();
  const { user } = useContext(UserContext);

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/post/all/${categoryId}`);
        let posts = response.data;

        const commentsForPostsResp = await axios.post('http://localhost:8080/favourites/check-favourite-posts-for-user',
          {userId: user.id, postsToCheck: response.data.map(post => post.id)});

        let commentsForPosts = commentsForPostsResp.data.favoritePostsMappings;
        posts.map(post => {
          post.isFavorite = commentsForPosts[post.id];
        });

        setPosts(posts);
      } catch (error) {
        console.error('There was an error!', error);
      }
    };

    fetchPosts();
  }, [categoryId]);
  // todo: fetch the category name and display it in the heading instead of categoryId

  return (
    <div className="category-posts-main">
      <h1>All Post Listings for Category {categoryId}</h1>
      {posts.map(post => <PostOverview key={post.id} post={post} />)}
    </div>
  );
}

export default PostsForCategory;