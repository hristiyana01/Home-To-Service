import { Navbar, Nav, Container } from 'react-bootstrap';

function NavbarComponent() {
  return (
    <Navbar bg="light" expand="lg">
      <Container>
        <Navbar.Brand href="index.html">Home</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link href="#news">News</Nav.Link>
            <Nav.Link href="#categories-list">Categories</Nav.Link>
            <Nav.Link href="#posts">All Posts</Nav.Link>
            <Nav.Link href="contact">Contact</Nav.Link>
            <Nav.Link href="#about">About</Nav.Link>
            <Nav.Link href="#test">Test</Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default NavbarComponent;