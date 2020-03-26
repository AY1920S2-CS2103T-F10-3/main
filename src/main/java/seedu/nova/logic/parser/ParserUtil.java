package seedu.nova.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.nova.commons.core.index.Index;
import seedu.nova.commons.util.StringUtil;
import seedu.nova.logic.parser.exceptions.ParseException;
import seedu.nova.model.category.Category;
import seedu.nova.model.person.Email;
import seedu.nova.model.person.Name;
import seedu.nova.model.person.Phone;
import seedu.nova.model.plan.TaskFreq;
import seedu.nova.model.progresstracker.Project;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    /**
     * The constant MESSAGE_INVALID_INDEX.
     */
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_WEEK = "Week is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @param oneBasedIndex the one based index
     * @return the index
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param name the name
     * @return the name
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param phone the phone
     * @return the phone
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param email the email
     * @return the email
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Category}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param tag the tag
     * @return the tag
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Category parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Category.isValidTagName(trimmedTag)) {
            throw new ParseException(Category.MESSAGE_CONSTRAINTS);
        }
        return new Category(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Category>}.
     *
     * @param tags the tags
     * @return the set
     * @throws ParseException the parse exception
     */
    public static Set<Category> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Category> categorySet = new HashSet<>();
        for (String tagName : tags) {
            categorySet.add(parseTag(tagName));
        }
        return categorySet;
    }

    public static LocalTime parseTime(String time) {
        return LocalTime.parse(time.trim());
    }

    /**
     * Parses {@code String day} into a {@code DayOfWeek}.
     */
    public static DayOfWeek parseDay(String day) {
        day = day.toUpperCase();
        DayOfWeek d = DayOfWeek.valueOf(day);

        return d;
    }

    /**
     * Parsers week number into an int
     *
     * @param week string containing number to specify the week
     * @return int of the week input
     * @throws ParseException if is non-zero throw exception
     */
    public static int parseWeek(String week) throws ParseException {
        requireNonNull(week);
        String trimmedWeek = week.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedWeek)) {
            throw new ParseException(MESSAGE_INVALID_WEEK);
        }
        return Integer.parseInt(trimmedWeek);
    }

    /**
     * Checks if project name is correct
     *
     * @param project project name
     * @return project name
     * @throws ParseException if project name is wrong
     */
    public static String parseProject(String project) throws ParseException {
        requireNonNull(project);
        String trimmedProject = project.trim();

        if (!Project.isValidProject(trimmedProject)) {
            throw new ParseException(Project.MESSAGE_CONSTRAINTS);
        }

        return trimmedProject;
    }

    /**
     * Parse date local date.
     *
     * @param date the date
     * @return the local date
     */
    public static LocalDate parseDate(String date) {
        requireNonNull(date);
        String trimmedDate = date.trim();
        return LocalDate.parse(trimmedDate);
    }

    /**
     * Parse minutes into Duration
     *
     * @param min minutes
     * @return Duration
     * @throws ParseException
     */
    public static Duration parseMinuteDuration(String min) throws ParseException {
        requireNonNull(min);
        String trimmedMin = min.trim();
        return Duration.ofMinutes(Long.parseLong(trimmedMin));
    }

    /**
     * Parse task frequency to TaskFreq
     *
     * @param freq frequency
     * @return TaskFreq
     * @throws ParseException
     */
    public static TaskFreq parseTaskFreq(String freq) throws ParseException {
        requireNonNull(freq);
        String trimmedFreq = freq.trim();
        return TaskFreq.valueOf(trimmedFreq);
    }
}
