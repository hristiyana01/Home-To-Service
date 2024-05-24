import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart } from '@fortawesome/free-regular-svg-icons';
import { faHeart as solidHeart } from '@fortawesome/free-solid-svg-icons';
import axios from "axios";


function PostOverview({ post }) {
  const date = new Date(post.createdDate);
  const formattedDate = date.toLocaleDateString();
  const formattedTime = date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });

  const handleFavouriteIconClick = async () => {
    try {
      const response = await axios.post('http://localhost:8080/favourites/add', {
        userId: 1,
        post: post.id
      });
      console.log(response.data);
    } catch (error) {
      console.error('There was an error!', error);
    }
  };

  return (
    <div key={post.id} className="post">
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
          post?.isFavorite || false ?
            (<FontAwesomeIcon onClick={() => handleFavouriteIconClick(post.id, post.isFavorite)} icon={solidHeart} />)
            : (<FontAwesomeIcon onClick={()=> handleFavouriteIconClick(post.id, post.isFavorite)} icon={faHeart} />)
        }
        </div>
        {/*<FontAwesomeIcon onClick={() => handleFavouriteIconClick(post.id, post.isFavorite)} icon={faHeart} />*/}
        {/*<FontAwesomeIcon icon={solidHeart} />*/}

        <div>
          <p className="post-price">{post.price} BGN</p>
        </div>
      </div>
    </div>
  );
}

export default PostOverview;