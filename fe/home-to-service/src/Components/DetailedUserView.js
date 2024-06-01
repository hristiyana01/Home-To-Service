import React, { useEffect, useContext, useState } from 'react';
import { useParams } from 'react-router-dom';
import { UserContext } from './App';
import axios from 'axios';

export default function UserDetailsPage() {
  const { userId } = useParams();
  const { user } = useContext(UserContext);
  const [userDetails, setUserDetails] = useState(null);
  const [userPosts, setUserPosts] = useState([]);
  const [userReviews, setUserReviews] = useState([]);

  useEffect(() => {
    const fetchUserDetails = async () => {
      try {
        const response = await axios.get(`/user/get/${userId}`);
        setUserDetails(response.data);
      } catch (error) {
        console.error('Error fetching user details:', error);
      }
    };

    const fetchUserPosts = async () => {
      try {
        const response = await axios.get(`/post/user/${postId}`);
        setUserPosts(response.data);
      } catch (error) {
        console.error('Error fetching user posts:', error);
      }
    };

    const fetchUserReviews = async () => {
      try {
        const response = await axios.get(`/user/${userId}/reviews`);
        setUserReviews(response.data);
      } catch (error) {
        console.error('Error fetching user reviews:', error);
      }
    };

    fetchUserDetails();
    fetchUserPosts();
    fetchUserReviews();
  }, [userId]);

  if (!userDetails) return <div>Loading user details...</div>;

  return (
    <div>
      <h1>User Details</h1>
      <div>
        <h2>{userDetails.name}</h2>
        <p>Email: {userDetails.email}</p>
        {/* Add more user details here */}
      </div>
      <div>
        <h2>User Posts</h2>
        <ul>
          {userPosts.map(post => (
            <li key={post.id}>{post.title}</li>
          ))}
        </ul>
      </div>
      <div>
        <h2>User Reviews</h2>
        <ul>
          {userReviews.map(review => (
            <li key={review.id}>{review.content}</li>
          ))}
        </ul>
      </div>
    </div>
  );
}
