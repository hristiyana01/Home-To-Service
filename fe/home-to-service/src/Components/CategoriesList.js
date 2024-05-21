import React, { useEffect, useState } from 'react';
import axios from "axios";
import '../styles.css';
import {Link} from "react-router-dom"; // Import the CSS file

function CategoriesList() {
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const response = await axios.get('http://localhost:8080/category/getAll');
        setCategories(response.data);
      } catch (error) {
        console.error('There was an error!', error);
      }
    };

    fetchCategories();
  }, []);

  return (
    <div>
      <h1 className="categories-heading">Categories</h1>
      <div className="categories-grid">
        {categories.map(category => (
          <Link to={`/posts/category/${category.id}`} key={category.id} className="category-anchor">
            <div className="category-card">
              <img className="category-image" src="https://dummyimage.com/100x100/dee2e6/6c757d.jpg" alt={category.name} />
              <h2 className="category-name">{category.name}</h2>
            </div>
          </Link>
        ))}
      </div>
    </div>
  );
}

export default CategoriesList;