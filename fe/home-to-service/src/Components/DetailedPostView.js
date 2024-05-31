import React, {useContext, useEffect, useState} from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart } from '@fortawesome/free-regular-svg-icons';
import { faHeart as solidHeart } from '@fortawesome/free-solid-svg-icons';
import axios from "axios";
import {useParams} from "react-router-dom";
import {UserContext} from "./App";
export default DetaileldPostView;

function DetaileldPostView() {
  const { postId } = useParams();
  const { user } = useContext(UserContext);
  const [post, setPost] = useState(null);
  const [isFavorite, setIsFavorite] = useState(false);

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/post/${postId}`);
        let postdata = response.data;
        console.log(postdata);

        const favoritePostMappings = await axios.post(`http://localhost:8080/favourites/check-favourite-posts-for-user`,
          {userId: user.id, postsToCheck: [postId]});

        console.log(favoritePostMappings);

        const date = new Date(postdata.createdDate);
        postdata.formattedDate = date.toLocaleDateString();
        post.formattedTime = date.toLocaleTimeString([], {hour: '2-digit', minute: '2-digit'});

        setPost(postdata);
        setIsFavorite(favoritePostMappings.data.favoritePostsMappings[post.id]);
      } catch (error) {
        console.error('There was an error!', error);
      }
    };

    fetchPosts();
  }, [postId]);

  const handleFavouriteIconClick = async () => {
    try {
      const response = await axios.post('http://localhost:8080/favourites/toggle', {
        userId: user.id,
        postId: postId
      });

      setIsFavorite(response.data);
    } catch (error) {
      console.error('There was an error!', error);
    }
  };
  //prosto ne si pisala fe za towa mislish taka, ne che i realen backend si pisala :D
  //.... :P
  if(!post) return 'Loading...';

  return (
    <div key={post.id} className="post">
      <img className="post-image" src="https://dummyimage.com/150x150/dee2e6/6c757d.jpg" alt={post.title} />
      <div className="post-details">
        <h2>{post.title}</h2>
        <div className="post-description">
          <p className="post-location">{post.location}</p>
          <p className="post-date">{`${post.formattedDate}, ${post.formattedTime}`}</p>
        </div>
      </div>
      <div className="post-overview-rightside">
        <div className="post-heart-container">
          {
            isFavorite || false ?
              (<FontAwesomeIcon onClick={() => handleFavouriteIconClick()} icon={solidHeart} />)
              : (<FontAwesomeIcon onClick={()=> handleFavouriteIconClick()} icon={faHeart} />)
          }
        </div>
        <div>
          <p className="post-price">{post.price} BGN</p>
        </div>
      </div>
    </div>
  );
}

