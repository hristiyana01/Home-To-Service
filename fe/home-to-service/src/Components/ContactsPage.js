import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faInstagram, faFacebook, faTwitter } from '@fortawesome/free-brands-svg-icons';
import contactImage from '../HomeToService-Contacts.jpg';
import {Navbar} from "react-bootstrap";


function ContactsPage() {
  return (
    <div>
      <Navbar>
        <Navbar.Brand href="#home">
          <img
            src={contactImage}
            width="10"
            height="10"
            className="d-inline-block align-top"
            alt="Logo"
          />
        </Navbar.Brand>
      </Navbar>
      <img src={contactImage} alt="Contact" /> {/* Use the contact image */}
      <h1>Contact Us</h1>
      <p>Phone: +1 234 567 890</p>
      <p>Address: 123 Main St, Anytown, USA</p>
      <h2>Follow us on social media</h2>
      <a href="https://www.instagram.com/yourusername" target="_blank" rel="noopener noreferrer">
        <FontAwesomeIcon icon={faInstagram} /> Instagram
      </a><br/>
      <a href="https://www.facebook.com/yourusername" target="_blank" rel="noopener noreferrer">
        <FontAwesomeIcon icon={faFacebook} /> Facebook
      </a><br/>
      <a href="https://www.twitter.com/yourusername" target="_blank" rel="noopener noreferrer">
        <FontAwesomeIcon icon={faTwitter} /> Twitter
      </a>
    </div>
  );
}

export default ContactsPage;