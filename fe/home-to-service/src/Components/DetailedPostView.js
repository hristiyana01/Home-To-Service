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
  const [creatorDetails, setCreatorDetails] = useState(null);
  const [isFavorite, setIsFavorite] = useState(false);
  const [comments, setComments] = useState([]);
  const [images, setImages] = useState([]);
  //trqbva da gi prisvoish kato state gore dannite ot 2te zaqvki 
  //tuk pazish dannite ot zaqvkite
  //tvoq post durji mnogo danni i trqbva da gi renderirash 
  //nov component ako iskash da preizpolzvash Favorite logikata i da e spodelena !
  
  useEffect(() => {
    const fetchPosts = async () => {     
      try {
        const postResponse = await axios.get(`http://localhost:8080/post/${postId}`);
        const postdata = postResponse.data;

      if (postdata && postdata.post) {
        if(postdata.post.createdDate){
          const date = new Date(postdata.post.createdDate);
          postdata.post.formattedDate = date.toLocaleDateString();
          postdata.post.formattedTime = date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });    
        }      

        setPost(postdata.post);
        setComments(postdata.comments);  
        setCreatorDetails(postdata.user);
        setImages(postdata.images);
        
        const favoritePostMappings = await axios.post(`http://localhost:8080/favourites/check-favourite-posts-for-user`,
          {userId: user.id, postsToCheck: [postId]});
        
      console.log('Favorite post mappings:', favoritePostMappings.data);
      setIsFavorite(favoritePostMappings.data.favoritePostsMappings[postId]);
    } else {
      console.error('Post object is null in the API response');
    }
  } catch (error) {
    console.error('There was an error!', error);
      }
    };

    fetchPosts();
  }, [postId, user.id]);

  const handleFavouriteIconClick = async (e) => {
    e.stopPropagation();
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
  
  if(!post) return 'Loading...';

  return (
    
    <div key={post.id} className="detailed-post">
      <div className="detailed-post-images">
      <img className="detailed-post-image" src="https://dummyimage.com/400x250/dee2e6/6c757d.jpg" alt={post.title} />
      </div>
      <div className="post-details">
        <h2>{post.title}</h2>
        <div className="post-description">
          <p>{post.description}</p>
          <p>{post.location}</p>
          {post.formattedDate && post.formattedTime && <p>{`${post.formattedDate}, ${post.formattedTime}`}</p> }
        </div>
      </div>
      <div>
        <div>
          {
            isFavorite || false ?
              (<FontAwesomeIcon onClick={handleFavouriteIconClick} icon={solidHeart} />)
              : (<FontAwesomeIcon onClick={handleFavouriteIconClick} icon={faHeart} />)
          }
        </div>
        <div>
          <p>{post.price} BGN</p>
        </div>
      </div>
      <div className="post-comments">
        <h3>Comments</h3>
        {comments.length > 0 ? (
          <ul>
            {comments.map(comment => (
              <li key={comment.id}>
                <p><strong>{comment.user}</strong>: {comment.text}</p>
              </li>
            ))}
          </ul>
        ) : (
          <p>No comments yet.</p>
        )}
      </div>
    </div>
  );
}

