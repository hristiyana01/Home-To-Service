import { useEffect, useState } from "react";
import axios from "axios";
import { useStore } from "../../stores/stores";
import { Link } from "react-router-dom";

export default function UserDetailsPage() {
  const { userStore } = useStore();
  const [userDetails, setUserDetails] = useState<any>({});
  const [reviews, setReviews] = useState<any>([]);
  const [userPosts, setUserPosts] = useState<any>([]);

  useEffect(() => {
    const fetchUserDetails = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/posts/user/${Number(userStore.userId)}`
        );
        const reviewsResponse = await axios.get(
          `http://localhost:8080/api/reviews/${Number(userStore.userId)}`
        );

        setUserPosts(response.data);
        setReviews(reviewsResponse.data);
      } catch (error) {
        console.error("Error fetching user details:", error);
      }
    };

    fetchUserDetails();
    setUserDetails(userStore.userData);
  }, [userStore.userId]);

  if (!userDetails) return <div>Loading user details...</div>;

  return (
    <div className="container my-3">
      <h1 className="text-center fw-bold">User Details</h1>
      <div className="text-center">
        <h2>{userDetails.name + ` ` + userDetails.surname}</h2>
        <p>Email: {userDetails.email}</p>
        <p>Username: {userDetails.username}</p>
        <p>Phone: {userDetails.phone_number}</p>
        <p>Country: {userDetails.country}</p>
        <p>City: {userDetails.city}</p>
        <h3>Post created by this user: {userDetails.userPosts}</h3>
      </div>
      <div>
        <h2>User Posts</h2>
        {userPosts.length > 0 && (
          <ul>
            {userPosts.map((post: any) => (
              <Link
                to={`/posts/${post.id}`}
                key={post.id}
                className="post-link"
              >
                <div className="post">
                  <img
                    className="post-image"
                    src={
                      post.image
                        ? post.image.split("/public")[1]
                        : "https://dummyimage.com/80x80/dee2e6/6c757d.jpg"
                    }
                    alt={post.title}
                  />
                  <div className="post-details">
                    <h2>{post.title}</h2>
                    <p>{post.location}</p>
                    <p>
                      {`${new Date(
                        post.createdDate
                      ).toLocaleDateString()}, ${new Date(
                        post.createdDate
                      ).toLocaleTimeString([], {
                        hour: "2-digit",
                        minute: "2-digit",
                      })}`}
                    </p>
                  </div>
                  <p className="post-price">{post.price} BGN</p>
                </div>
              </Link>
            ))}
          </ul>
        )}

        {userPosts.length === 0 && <p>User has no posts</p>}
      </div>
      {
        <div>
          <h2>User Reviews</h2>
          {reviews.length > 0 ? (
            <div>
              {reviews.map((review: any, i: number) => (
                <p key={i}>
                  <strong className="text-primary">{review.username}</strong>:{" "}
                  <span>{"⭐".repeat(review.rating)}</span>
                </p>
              ))}
            </div>
          ) : (
            <p>No reviews yet.</p>
          )}
        </div>
      }
    </div>
  );
}
