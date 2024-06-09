import { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";
import { useStore } from "../../stores/stores";
import { Button, Form } from "react-bootstrap";

export default function PostUserDetails() {
  const { userId } = useParams();
  const { userStore } = useStore();
  const [userDetails, setUserDetails] = useState<any>({});
  const [userPosts, setUserPosts] = useState<any>([]);
  const [reviews, setReviews] = useState<any>([]);
  const [newReview, setNewReview] = useState("");

  useEffect(() => {
    const fetchUserDetails = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/posts/user/${Number(userId)}`
        );

        const userResponse = await axios.get(
          `http://localhost:8080/api/users/getuser/${Number(userId)}`
        );

        const reviewsResponse = await axios.get(
          `http://localhost:8080/api/reviews/${Number(userId)}`
        );

        setUserDetails(userResponse.data);
        setUserPosts(response.data);
        setReviews(reviewsResponse.data);
      } catch (error) {
        console.error("Error fetching user details:", error);
      }
    };

    fetchUserDetails();
  }, []);

  const handleReviewSubmit = async (e: any) => {
    e.preventDefault();

    try {
      await axios.post(`http://localhost:8080/api/reviews/create`, {
        reviewedUserId: userId,
        reviewerId: userStore.userId,
        rating: parseInt(newReview), // Convert the rating to an integer
      });
      setNewReview(""); // Clear the newReview state
      location.reload();
    } catch (error) {
      console.error("There was an error!", error);
    }
  };

  if (!userDetails) return <div>Loading user details...</div>;

  return (
    <div>
      <h1 className="text-center fw-bold">User Details</h1>
      <div className="text-center">
        <h2>{userDetails.name + ` ` + userDetails.surname}</h2>
        <p>Email: {userDetails.email}</p>
        <p>Username: {userDetails.username}</p>
        <p>Phone: {userDetails.phone_number}</p>
        <p>Country: {userDetails.country}</p>
        <p>City: {userDetails.city}</p>
        <p>
          Rating:{" "}
          {reviews.length > 0
            ? (
                reviews.reduce(
                  (accumulator: number, current: any) =>
                    accumulator + current.rating,
                  0
                ) / reviews.length
              ).toFixed(2)
            : "0.00"}
          /6
        </p>

        <p>Post created by this user: {userDetails.userPosts}</p>
      </div>
      <div>
        <h2>User Posts</h2>
        <ul>
          {userPosts.map((post: any) => (
            <Link to={`/posts/${post.id}`} key={post.id} className="post-link">
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
      </div>

      <div>
        <h2>User Reviews</h2>
        {reviews.length > 0 ? (
          <ul>
            {reviews.map((review: any, i: number) => (
              <li key={i}>{"⭐".repeat(review.rating)}</li>
            ))}
          </ul>
        ) : (
          <p>No reviews yet.</p>
        )}

        {Number(userStore.userId) !== Number(userId) && (
          <Form onSubmit={handleReviewSubmit}>
            <Form.Group controlId="review">
              <Form.Label>Add a Review</Form.Label>
              <Form.Control
                as="select"
                value={newReview}
                onChange={(e) => setNewReview(e.target.value)}
              >
                <option value="">Select your rating</option>
                <option value="1">⭐</option>
                <option value="2">⭐⭐</option>
                <option value="3">⭐⭐⭐</option>
                <option value="4">⭐⭐⭐⭐</option>
                <option value="5">⭐⭐⭐⭐⭐</option>
                <option value="6">⭐⭐⭐⭐⭐⭐</option>
              </Form.Control>
            </Form.Group>
            <Button variant="primary" type="submit" className="mt-2">
              Submit
            </Button>
          </Form>
        )}
      </div>
    </div>
  );
}
