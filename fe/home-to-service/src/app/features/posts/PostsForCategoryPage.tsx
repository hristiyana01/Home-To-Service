import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useStore } from "../../stores/stores";
import PostOverview from "./PostOverviewPage";

function PostsForCategory() {
  const { userStore } = useStore();
  const [posts, setPosts] = useState([]);
  const { categoryId } = useParams();
  const [category, setCategory] = useState<any>({});

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/posts/all/${categoryId}`
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

        const categoryResponse = await axios.get(
          `http://localhost:8080/api/categories/get/${categoryId}`
        );

        setPosts(posts);
        setCategory(categoryResponse.data);
      } catch (error) {
        console.error("There was an error!", error);
      }
    };

    fetchPosts();
  }, [categoryId]);

  return (
    <div className="category-posts-main">
      {category && <h1 className="text-center">{category.name} Posts</h1>}
      {posts.length !== 0 &&
        posts.map((post: any) => <PostOverview key={post.id} post={post} />)}

      {posts.length === 0 && (
        <h3 className="text-center mt-4">No posts yet!</h3>
      )}
    </div>
  );
}

export default PostsForCategory;
