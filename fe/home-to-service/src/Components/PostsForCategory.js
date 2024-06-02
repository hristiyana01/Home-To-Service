import React, {useContext, useEffect, useState} from 'react';
import axios from "axios";
import {useParams } from 'react-router-dom';
import PostOverview from "./PostOverview";
import {UserContext} from "./App";

function PostsForCategory() {
  const [posts, setPosts] = useState([]);
  const { categoryId } = useParams();
  const { user } = useContext(UserContext);
  const [category, setCategory] = useState(null);

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/post/all/${categoryId}`);
        let posts = response.data;

        const favoritesForPostsResp = await axios.post('http://localhost:8080/favourites/check-favourite-posts-for-user',
          {userId: user.id, postsToCheck: response.data.map(post => post.id)});

        let favoritePostsMappings = favoritesForPostsResp.data.favoritePostsMappings;
        posts.map(post => {
          post.isFavorite = favoritePostsMappings[post.id];
        });

        const categoryResponse = await axios.get(`http://localhost:8080/category/get/${categoryId}`);
        
        setPosts(posts);
        setCategory(categoryResponse.data);
      } catch (error) {
        console.error('There was an error!', error);
      }
    };

    fetchPosts();
  }, [categoryId]);
  // todo: fetch the category name and display it in the heading instead of categoryId

  return (
    <div className="category-posts-main">
      {category && <h1>All Posts for {category.name} Category</h1>}
      {posts.map(post => <PostOverview key={post.id} post={post} />)}
    </div>
  );
}

export default PostsForCategory;