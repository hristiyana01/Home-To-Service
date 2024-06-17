import { useEffect, useState } from "react";
import axios from "axios";
import "./PostsPage.css";
import toast from "react-hot-toast";
import { useStore } from "../../stores/stores";
import PostOverview from "./PostOverviewPage";

export default function PostsPage() {
  const { userStore } = useStore();
  const [posts, setPosts] = useState<DisplayPostModel[]>([]);

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/posts/all");

        let posts = response.data;

        if (userStore.isLoggedIn) {
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
        }
        setPosts(response.data);
      } catch (error) {
        toast.error("Our services might be down. Please try again later!");
      }
    };

    fetchPosts();
  }, []);

  return (
    <div className="category-posts-main">
      <h1 className="text-center">All Posts</h1>
      <br />

      <div className="post-grid">
        {posts.map((post) => (
          <PostOverview key={post.id} post={post} />
        ))}
      </div>
    </div>
  );
}
