import React, {useContext, useState} from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart } from '@fortawesome/free-regular-svg-icons';
import { faHeart as solidHeart } from '@fortawesome/free-solid-svg-icons';
import axios from "axios";
import {useNavigate} from "react-router-dom";
import {UserContext} from "./App";

export default PostOverview;

function PostOverview({ post }) {
  const { user } = useContext(UserContext);
  const navigate = useNavigate();
  const [isFavorite, setIsFavorite] = useState(post.isFavorite);
  const date = new Date(post.createdDate);
  const formattedDate = date.toLocaleDateString();
  const formattedTime = date.toLocaleTimeString([], {hour: '2-digit', minute: '2-digit'});

  const handleFavouriteIconClick = async (e) => {
    e.stopPropagation();
    try {
      const response = await axios.post('http://localhost:8080/favourites/toggle', {
        userId: user.id,
        postId: post.id
      });

      setIsFavorite(response.data);
    } catch (error) {
      console.error('There was an error!', error);
    }
  };

  const handlePostClick = () => {
    navigate(`/posts/${post.id}`); 
  }

  return (
    <div key={post.id} className="post" onClick={handlePostClick}>
      <img className="post-image" src="https://dummyimage.com/150x150/dee2e6/6c757d.jpg" alt={post.title} />
      <div className="post-details">
        <h2>{post.title}</h2>
        <div className="post-description">
          <p className="post-location">{post.location}</p>
          <p className="post-date">{`${formattedDate}, ${formattedTime}`}</p>
        </div>
      </div>
      <div className="post-overview-rightside">
        <div className="post-heart-container">
          {
            isFavorite || false ?
              (<FontAwesomeIcon onClick={handleFavouriteIconClick} icon={solidHeart} />)
              : (<FontAwesomeIcon onClick={handleFavouriteIconClick} icon={faHeart} />)
          }
        </div>
        <div>
          <p className="post-price">{post.price} BGN</p>
        </div>
      </div>
    </div>
  );
}