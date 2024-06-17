import { useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHeart } from "@fortawesome/free-regular-svg-icons";
import {
  faCalendarDays,
  faCoins,
  faHeart as solidHeart,
} from "@fortawesome/free-solid-svg-icons";
import axios from "axios";
import { Link } from "react-router-dom";
import { useStore } from "../../stores/stores";
import { faLocationDot } from "@fortawesome/free-solid-svg-icons/faLocationDot";

export default function PostOverview({ post }: any) {
  const { userStore } = useStore();
  const [isFavorite, setIsFavorite] = useState(false);

  useEffect(() => {
    setIsFavorite(post.isFavorite);
  }, []);

  const handleFavouriteIconClick = async (e: any) => {
    e.stopPropagation(); // Prevent the event from propagating to the parent (Link)
    try {
      const response = await axios.post(
        "http://localhost:8080/api/favourites/toggle",
        {
          userId: userStore.userId,
          postId: post.id,
        }
      );

      setIsFavorite(response.data);
    } catch (error) {
      console.error("There was an error!", error);
    }
  };

  return (
    <>
      <div className="post">
        <Link to={`/posts/${post.id}`} className="post-link">
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
            <p>
              <FontAwesomeIcon icon={faLocationDot} />
              {`   ` + post.location}
            </p>
            <p>
              <FontAwesomeIcon icon={faCalendarDays} />{" "}
              {`${new Date(post.createdDate).toLocaleDateString()}, ${new Date(
                post.createdDate
              ).toLocaleTimeString([], {
                hour: "2-digit",
                minute: "2-digit",
              })}`}
            </p>
          </div>
        </Link>
        <div className="post-overview-rightside">
          {userStore.isLoggedIn && (
            <div className="post-heart-container">
              {isFavorite || false ? (
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
          )}
          <p className="post-price">
            <FontAwesomeIcon icon={faCoins} /> {post.price} BGN
          </p>
        </div>
      </div>
    </>
  );
}
