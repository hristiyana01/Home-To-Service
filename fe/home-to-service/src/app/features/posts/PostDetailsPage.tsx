import { useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHeart } from "@fortawesome/free-regular-svg-icons";
import { faHeart as solidHeart } from "@fortawesome/free-solid-svg-icons";
import axios from "axios";
import { Link, useNavigate, useParams } from "react-router-dom";
import { Carousel, Button, Form } from "react-bootstrap";
import { useStore } from "../../stores/stores";

export default function DetailedPostView() {
  const navigate = useNavigate();
  const { userStore } = useStore();
  const { postId } = useParams();
  const [post, setPost] = useState<DisplayPostModel>();
  const [creatorDetails, setCreatorDetails] = useState<any>({});
  const [isFavorite, setIsFavorite] = useState(false);
  const [comments, setComments] = useState<any[]>([]);
  const [images, setImages] = useState<string[]>([]);
  const [newComment, setNewComment] = useState("");
  const [reviews, setReviews] = useState<any>([]);

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const postResponse = await axios.get(
          `http://localhost:8080/api/posts/${postId}`
        );

        if (postResponse.data.id) {
          if (postResponse.data.createdDate) {
            const date = new Date(postResponse.data.createdDate);
            postResponse.data.formattedDate = date.toLocaleDateString();
            postResponse.data.formattedTime = date.toLocaleTimeString([], {
              hour: "2-digit",
              minute: "2-digit",
            });
          }

          const imagesResponse = await axios.get<string[]>(
            `http://localhost:8080/api/images/${postResponse.data.id}`
          );

          const commentsResponse = await axios.get(
            `http://localhost:8080/api/comments/${postResponse.data.id}`
          );

          const reviewsResponse = await axios.get(
            `http://localhost:8080/api/reviews/${Number(
              postResponse.data.user.id
            )}`
          );

          setPost(postResponse.data);
          setComments(commentsResponse.data);
          setCreatorDetails(postResponse.data.user);
          setImages(imagesResponse.data);
          setReviews(reviewsResponse.data);

          const favoritePostMappings = await axios.post(
            `http://localhost:8080/api/favourites/check-favourite-posts-for-user`,
            { userId: userStore.userId, postsToCheck: [postId] }
          );

          setIsFavorite(
            favoritePostMappings.data.favoritePostsMappings[Number(postId)]
          );
        } else {
          console.error("Post object is null in the API response");
        }
      } catch (error) {
        console.error("There was an error!", error);
      }
    };

    fetchPosts();
  }, [postId, userStore.userId]);

  const handleCommentSubmit = async (e: any) => {
    e.preventDefault();
    if (newComment.trim() === "") return;

    try {
      await axios.post(`http://localhost:8080/api/comments/create`, {
        postId: postId,
        userId: userStore.userId,
        text: newComment,
      });
      setNewComment("");
      location.reload();
    } catch (error) {
      console.error("There was an error!", error);
    }
  };

  const handleFavouriteIconClick = async (e: any) => {
    e.stopPropagation();
    try {
      const response = await axios.post(
        "http://localhost:8080/api/favourites/toggle",
        {
          userId: userStore.userId,
          postId: postId,
        }
      );

      setIsFavorite(response.data);
    } catch (error) {
      console.error("There was an error!", error);
    }
  };

  if (!post) return "Loading...";

  const handleEditButtonClick = () => {
    navigate(`/posts/edit/${postId}`);
  };

  return (
    <div className="container my-3">
      <div className="row">
        <div className="col-12 col-lg-8">
          <div key={post?.id} className="d-flex flex-column">
            <div className="detailed-post-images">
              {images.length > 0 ? (
                <Carousel>
                  {images.map((image, index) => (
                    <Carousel.Item key={index}>
                      <img
                        className="d-block w-100"
                        src={image.split("/public")[1]}
                        alt={`Slide ${index}`}
                      />
                    </Carousel.Item>
                  ))}
                </Carousel>
              ) : (
                <img
                  className="detailed-post-image"
                  src="https://dummyimage.com/400x250/dee2e6/6c757d.jpg"
                  alt={post.title}
                />
              )}
            </div>
            <div className="d-flex flex-column justify-content-center align-items-center">
              <Form
                onSubmit={handleCommentSubmit}
                className="mt-4 d-flex flex-column justify-content-center align-items-center"
              >
                <Form.Group controlId="comment">
                  <Form.Label className="fs-4">Add a Comment</Form.Label>
                  <Form.Control
                    type="text"
                    placeholder="Enter your comment"
                    value={newComment}
                    onChange={(e) => setNewComment(e.target.value)}
                  />
                </Form.Group>
                <Button variant="primary" type="submit" className="mt-2">
                  Submit
                </Button>
              </Form>

              <div className="post-comments mt-5">
                <h3>Comments</h3>
                {comments.length > 0 ? (
                  <div>
                    {comments.map((comment) => (
                      <p>
                        <strong className="text-primary">
                          {comment.username}
                        </strong>
                        : <span>{comment.commentText}</span>
                      </p>
                    ))}
                  </div>
                ) : (
                  <p>No comments yet.</p>
                )}
              </div>
            </div>
          </div>
        </div>

        <div className="col-12 col-lg-4">
          <div className="pb-0 d-flex flex-column align-items-center">
            <h2 className="text-center">{post?.title}</h2>
            <div className="post-description">
              <p className="text-center">{post?.description}</p>
            </div>

            {userStore.userId === post.user.id && (
              <Button variant="warning" onClick={handleEditButtonClick}>
                Edit
              </Button>
            )}

            <div className="mt-2 d-flex flex-row justify-content-center align-items-center my-auto">
              <div className="mx-2 fs-5">
                {isFavorite ? (
                  <FontAwesomeIcon
                    onClick={handleFavouriteIconClick}
                    icon={solidHeart}
                  />
                ) : (
                  <FontAwesomeIcon
                    onClick={handleFavouriteIconClick}
                    icon={faHeart}
                  />
                )}
              </div>
              <div>
                <p className="fw-bold">{post.price} BGN</p>
              </div>
            </div>

            <p className="text-center">{post?.location}</p>
            {post?.formattedDate && post?.formattedTime && (
              <p className="text-center">{`${post.formattedDate}, ${post.formattedTime}`}</p>
            )}

            <div className="col-12 col-md-12 mt-3 text-center">
              <Link
                to={`/profile/${creatorDetails.id}`}
                className="text-decoration-none"
              >
                <span className="text-warning fs-4 fw-bold">
                  {creatorDetails.name + ` ` + creatorDetails.surname}
                </span>
              </Link>
            </div>

            <div className="col-12 col-md-12 mt-3 mb-3 text-center profileinfo pb-3">
              <p>Телефонен номер: {creatorDetails.phone_number}</p>
              <p>Локация: {post.location + `, ` + creatorDetails.country}</p>
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
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
