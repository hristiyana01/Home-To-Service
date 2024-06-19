import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import toast from "react-hot-toast";

export default function CategoriesPage() {
  const [categories, setCategories] = useState<CategoryModel[]>([]);

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/categories"
        );
        setCategories(response.data);
      } catch (error) {
        toast.error("Couldn't load categories. Try again later!");
      }
    };

    fetchCategories();
  }, []);

  return (
    <div className="container">
      <h1 className="text-center mt-3 mb-5">Categories</h1>

      <div className="row">
        {categories.map((category) => (
          <div
            key={category.id}
            className="col-12 col-sm-6 col-md-4 d-flex justify-content-center my-4"
          >
            <Link
              to={`/posts/category/${category.id}`}
              className="text-center text-decoration-none btn btn-outline-primary"
            >
              <p className="fs-5 fw-bold mx-auto my-auto">{category.name}</p>
            </Link>
          </div>
        ))}
      </div>
    </div>
  );
}
