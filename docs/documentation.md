# Main Documentation Info

This document describes the different documentation tree's and how to navigate this documentation.

## General context

All documentation should adhere to this master ruleset.

### Documentation Statusses

**Draft.** A document in the draft version is a documentation that is being drafted and has not been presented to the team yet.   
**Proposal.** A document in the proposal state is a document that has been proposed to the team and is undergoing feedback and changes.   
**Accepted.** A document in the Accepted state is a representation of the current rules that was accepted by the team.   
**Declined.** A document in the Declined state is a ruleset that was not accepted and should be redrafted.   
**Changes.** A document in the Changes state is a document that was previously accepted, but has changes that are being proposed.    
Everything under the title `proposed changes` are the changes under review. This is acted upon like the proposal state.   
Everything under the other titles are acted upon as the previous state defines, either Accepted, Declined or Depricated.   
**Depricated.** A document in the Depricated state is a document that is no longer up to date and should either be changed or removed.   

### Decision Makers

This should contain a list of all the team members that are involved in the creation of, giving feedback on and accepting a decision on rulesets.

Only teammembers that are currently on the project should be named, all team members on the project should follow the decided upon rules.
Every team member can file a deprication request to the Teamlead or Scrum Master of the project.

## Table of Documentation

1. ReadMe
2. Architectural
3. Cooperative

### ReadMe

The readme file should be the document that clearifies how to install the software and start developing on the project.

### Architectural

These documentations are specifically for project structure and architecture decisions. This is often also called an ADR (Architectural Decision Record)

This should contain:
- Features that must, may or may not be used from the Java language and frameworks
- Frameworks that may or may not be used in the project
- Style guide on how the code should be styled
- Main Domain Architecture and structuring
- Test Architecture and decisions

#### Naming of the documents

These documents are named as follows:
1. Pre-fix: The prefix of AD which stands for Architectural Decisions
2. id-number: The three digit unique code for the AD
3. -
4. Descriptive name of the AD. e.g.: Main Architecture, DTO rules, Java feature: Var, Java feature: sealed classes, ...

#### Current Documentation

1. Testing. This document decides on the ruleset for testing and how it should be implemented in the project.
2. Main architecture. This document decides on the main architecture of the project and the structuring of the project.

### Cooperative

These documentations are specifically for the ruleset on how the team works together.

This should contain:
- Communication rules and decisions
- Cooperation between team members, team and customer and more
- Tools and systems for constructive cooperation

#### Naming of the documents

These documents are named as follows:
1. Pre-fix: The prefix of CD, which stands for Cooperative Decisions
2. id-number: The three digit unique code for the CD
3. -
4. Descriptive name of the CD. e.g.: Git Flow, Continous Deployment, Continous Development, Scrum Framework Implementation, ...

#### Current Documentation

1. Git and Github. This document decides on the ruleset for the git flow and implementation via GitHub
