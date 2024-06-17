import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faInstagram,
  faFacebook,
  faXTwitter,
} from "@fortawesome/free-brands-svg-icons";

export default function ContactsPage() {
  return (
    <div className="d-flex flex-column align-items-center mt-3">
      <img
        src="/site-logo.jpg"
        alt="Contact"
        width={256}
        height={256}
        className="rounded"
      />
      <h1 className="mt-3">Contact Us</h1>
      <p>Phone: +359 088 888 88 88</p>
      <p>Address: Bulgaria, Sofia Center</p>
      <h2>Follow us on social media</h2>
      <div className="d-flex flex-row">
        <a
          className="text-decoration-none text-warning mx-2"
          href="https://www.instagram.com"
          target="_blank"
          rel="noopener noreferrer"
        >
          <FontAwesomeIcon icon={faInstagram} /> Instagram
        </a>
        <br />
        <a
          className="text-decoration-none text-primary mx-2"
          href="https://www.facebook.com"
          target="_blank"
          rel="noopener noreferrer"
        >
          <FontAwesomeIcon icon={faFacebook} /> Facebook
        </a>
        <br />
        <a
          className="text-decoration-none text-success mx-2"
          href="https://www.twitter.com"
          target="_blank"
          rel="noopener noreferrer"
        >
          <FontAwesomeIcon icon={faXTwitter} /> X (Twitter)
        </a>
      </div>
    </div>
  );
}
