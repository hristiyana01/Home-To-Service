import toast from "react-hot-toast";
import PostOverview from "../posts/PostOverviewPage";
import axios from "axios";
import { useEffect, useState } from "react";
import { useStore } from "../../stores/stores";

export default function FavoritesPage() {
  const { userStore } = useStore();
  const [posts, setPosts] = useState<DisplayPostModel[]>([]);

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/posts/favorites/${userStore.userId}`
        );

        let posts = response.data;

        const favoritesForPostsResp = await axios.post(
          "http://localhost:8080/api/favourites/check-favourite-posts-for-user",
          {
            userId: userStore.userId,
            postsToCheck: response.data.map((post: any) => post.id),
          }
        );

        let favoritePostsMappings =
          favoritesForPostsResp.data.favoritePostsMappings;
        posts.map((post: any) => {
          post.isFavorite = favoritePostsMappings[post.id];
        });
        setPosts(response.data);
      } catch (error) {
        toast.error("Our services might be down. Please try again later!");
      }
    };

    fetchPosts();
  }, []);

  return (
    <div className="category-posts-main">
      <h1 className="text-center">Your favorites</h1>
      <br />

      <div className="post-grid">
        {posts.map((post) => (
          <PostOverview key={post.id} post={post} />
        ))}
      </div>
    </div>
  );
}
